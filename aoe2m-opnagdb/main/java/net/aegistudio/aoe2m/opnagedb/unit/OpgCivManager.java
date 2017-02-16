package net.aegistudio.aoe2m.opnagedb.unit;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.unit.Civilization;
import net.aegistudio.aoe2m.opnagedb.CsvFilter;
import net.aegistudio.uio.media.Storage;

public class OpgCivManager implements AssetManager<Civilization> {
	protected final OpgCivilization[] civilizations;
	public OpgCivManager(AssetListener perfLog, Storage root) throws IOException {
		Storage gamedata = root.open("gamedata").open("gamedata-empiresdat");
		Storage civs = gamedata.open("0000-civs.docx");
		
		try(BufferedReader gamedataReader = new BufferedReader(new InputStreamReader(civs.read()))) {
			String[] lines = gamedataReader.lines().toArray(String[]::new);
			perfLog.initSubsystem(CIV_NAME, CIV_CLASS, lines.length);
			
			String[][] linesWrap = Arrays.stream(lines)
					.filter(CsvFilter::filter)
					.map(CsvFilter::map).toArray(String[][]::new);
			
			Map<Integer, String[]> civilMapping = new TreeMap<>();
			for(int i = 0; i < linesWrap.length; i ++) 
				civilMapping.put(i, linesWrap[i]);
			
			civilizations = civilMapping.entrySet().stream()
					.map(entry -> new OpgCivilization(
							entry.getKey(), perfLog, entry.getValue()))
					.sorted(OpgCivilization::compare)
					.toArray(OpgCivilization[]::new);
			
			perfLog.readySubsystem(CIV_NAME, CIV_CLASS);
		}
	}
	@Override
	public int max() {
		return civilizations.length;
	}
	
	@Override
	public Civilization query(int id) {
		if(id < 0 || id >= max()) return null;
		else return civilizations[id];
	}
	
	@Override
	public void iterate(BiConsumer<Integer, Civilization> iterator) {
		for(int i = 0; i < max(); i ++)
			if(query(i) != null) iterator.accept(i, query(i));
	}
}
