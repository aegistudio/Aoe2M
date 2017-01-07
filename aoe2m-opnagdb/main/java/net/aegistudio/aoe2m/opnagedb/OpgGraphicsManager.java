package net.aegistudio.aoe2m.opnagedb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;

public class OpgGraphicsManager implements AssetManager<GraphicsGamedata>{
	protected final OpgGraphicsGamedata[] graphics;
	public OpgGraphicsManager(File root) throws IOException {
		File parent = new File(root, "graphics");
		File graphicsGamedata = new File(new File(new File(root, "gamedata"), 
				"gamedata-empiresdat"), "0000-graphics.docx");
		
		try(BufferedReader gamedataReader = new BufferedReader(new FileReader(graphicsGamedata))) {
			OpgGraphicsGamedata[] tempGraphics = gamedataReader.lines()
				.filter(CsvFilter::filter).map(CsvFilter::map)
				.map(FunctionWrapper.mapIgnoreExcept(
						params -> new OpgGraphicsGamedata(parent, params)))
				.toArray(OpgGraphicsGamedata[]::new);
			
			int maxGraphics = 0;
			for(int i = 0; i < tempGraphics.length; i ++)
				if(tempGraphics[i] != null)
					maxGraphics = Math.max(tempGraphics[i].id, maxGraphics);
			
			graphics = new OpgGraphicsGamedata[maxGraphics];
			for(int i = 0; i < tempGraphics.length; i ++)
				if(tempGraphics[i] != null)
					graphics[tempGraphics[i].id] = tempGraphics[i];
		}
	}
	@Override
	public int max() {
		return graphics.length;
	}
	@Override
	public GraphicsGamedata query(int id) {
		return graphics[id];
	}
	
	@Override
	public void iterate(BiConsumer<Integer, GraphicsGamedata> iterator) {
		for(int i = 0; i < graphics.length; i ++)
			if(graphics[i] != null) iterator.accept(i, graphics[i]);
	}
}
