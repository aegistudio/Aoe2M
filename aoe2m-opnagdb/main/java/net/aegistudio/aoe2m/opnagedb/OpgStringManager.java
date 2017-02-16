package net.aegistudio.aoe2m.opnagedb;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.uio.media.Storage;

public class OpgStringManager {
	public final Map<String, OpgStringLocaleManager> localeMap = new TreeMap<>();
	public OpgStringManager(AssetListener perfLog, Storage root) throws IOException {
		Storage parent = root.open("string_resources.docx");
		
		try(BufferedReader gamedataReader = new BufferedReader(new InputStreamReader(parent.read()))) {
			String[] lines = gamedataReader.lines().toArray(String[]::new);
			perfLog.initSubsystem(LANGUAGE_NAME, LANGUAGE_CLASS, lines.length);
			
			Map<String, TreeMap<Integer, String>> substMap = new TreeMap<>();
			Arrays.stream(lines).filter(CsvFilter::filter)
				.map(line -> { try {
					String[] result = new String[3];
					
					int split1 = line.indexOf(',');
					result[0] = line.substring(0, split1);
					line = line.substring(split1 + 1);
					
					int split2 = line.indexOf(',');
					result[1] = line.substring(0, split2);
					result[2] = line.substring(split2 + 1);
					
					return result;
				} catch(Exception e) { return null; } })
				.filter(Objects::nonNull)
				.forEach(params -> {
					int id = Integer.parseInt(params[0]);
					String locale = params[1];
					perfLog.initAsset(LANGUAGE_NAME, LANGUAGE_CLASS, id);
					
					TreeMap<Integer, String> subst = substMap.get(locale);
					if(subst == null) substMap.put(locale, subst = new TreeMap<>());
					
					subst.put(id, replaceEscape(params[2]));
					
					perfLog.readyAsset(LANGUAGE_NAME, LANGUAGE_CLASS, id);
				});
			
			substMap.forEach((locale, subst) -> {
				int max = subst.keySet().stream().max(Integer::compare).get();
				localeMap.put(locale, new OpgStringLocaleManager(locale, max, subst));
			});
			
			perfLog.readySubsystem(LANGUAGE_NAME, LANGUAGE_CLASS);
		}	
	}
	
	protected String replaceEscape(String raw) {
		return raw.replace("\\n", "\n");
	}
	
	public OpgStringLocaleManager locale(String locale) {
		OpgStringLocaleManager current = localeMap.get(locale);
		return current != null? current : localeMap.values()
				.toArray(new OpgStringLocaleManager[0])[0];
	}
}

class OpgStringLocaleManager implements AssetManager<String> {
	public final String locale;
	public final int max;
	public final Map<Integer, String> pool;
	OpgStringLocaleManager(String locale, int max, Map<Integer, String> pool) {
		this.locale = locale;
		this.max = max;
		this.pool = pool;
	}
	
	@Override
	public int max() {
		return max;
	}
	
	@Override
	public String query(int id) {
		if(id < 0 || id >= max())
			return null;
		else return pool.get(id);
	}
	
	@Override
	public void iterate(BiConsumer<Integer, String> iterator) {
		pool.forEach(iterator);
	}
}