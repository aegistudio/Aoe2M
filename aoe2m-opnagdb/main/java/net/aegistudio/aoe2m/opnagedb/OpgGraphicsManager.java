package net.aegistudio.aoe2m.opnagedb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;

public class OpgGraphicsManager implements AssetManager<GraphicsGamedata>{
	protected final OpgGraphicsGamedata[] graphics;
	public OpgGraphicsManager(AssetListener perfLog, OpgPlayerPalette playerPalette, File root) throws IOException {
		File parent = new File(root, "graphics");
		File gamedata = new File(new File(root, "gamedata"), "gamedata-empiresdat");
		File graphicsDelta = new File(gamedata, "0000-graphics");
		File graphicsGamedata = new File(gamedata, "0000-graphics.docx");
		
		try(BufferedReader gamedataReader = new BufferedReader(new FileReader(graphicsGamedata))) {
			String[] lines = gamedataReader.lines().toArray(String[]::new);
			perfLog.initSubsystem(GRAPHICS_NAME, GRAPHICS_CLASS, lines.length);
			
			OpgGraphicsGamedata[] tempGraphics = Arrays.stream(lines)
				.filter(CsvFilter::filter).map(CsvFilter::map)
				.map(FunctionWrapper.mapIgnoreExcept(
						params -> new OpgGraphicsGamedata(perfLog, playerPalette, parent, graphicsDelta, params)))
				.toArray(OpgGraphicsGamedata[]::new);
			
			int maxGraphics = 0;
			for(int i = 0; i < tempGraphics.length; i ++)
				if(tempGraphics[i] != null)
					maxGraphics = Math.max(tempGraphics[i].id, maxGraphics);
			
			graphics = new OpgGraphicsGamedata[maxGraphics + 1];
			for(int i = 0; i < tempGraphics.length; i ++)
				if(tempGraphics[i] != null)
					graphics[tempGraphics[i].id] = tempGraphics[i];
			
			perfLog.readySubsystem(GRAPHICS_NAME, GRAPHICS_CLASS);
		}
	}
	@Override
	public int max() {
		return graphics.length;
	}
	@Override
	public GraphicsGamedata query(int id) {
		if(id < 0 || id >= graphics.length)
			return null;
		return graphics[id];
	}
	
	@Override
	public void iterate(BiConsumer<Integer, GraphicsGamedata> iterator) {
		for(int i = 0; i < graphics.length; i ++)
			if(graphics[i] != null) iterator.accept(i, graphics[i]);
	}
}
