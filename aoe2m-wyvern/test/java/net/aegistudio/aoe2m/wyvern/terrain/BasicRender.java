package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.SimpleTerrain;
import net.aegistudio.aoe2m.wyvern.tile.BasicRenderer;

public class BasicRender extends TerrainTestBase {
	public final BasicRenderer renderer;
	public final SimpleTerrain terrain = new SimpleTerrain(10, 10);
	public BasicRender() throws IOException, LWJGLException {
		super();
		renderer = new BasicRenderer(assetManager, textureManager, outline);
		for(int i = 0; i < 10; i ++)
			for(int j = 0; j < 10; j ++)
				terrain.elevation[i][j] = 1;
		
		terrain.elevation[4][4] = 0;
		terrain.elevation[4][5] = 0;
		terrain.elevation[4][6] = 0;
		
		terrain.elevation[5][4] = 0;
		terrain.elevation[5][5] = 0;
		terrain.elevation[5][6] = 0;
		
		terrain.elevation[6][4] = 0;
		terrain.elevation[6][5] = 0;
		terrain.elevation[6][6] = 0;
		
		terrain.elevation[8][8] = 2;
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		super.translateX = -400;
		super.translateY = 0;
		super.scale = 400;
	}
	
	@Override
	protected void renderTerrain() throws LWJGLException {
		renderer.prepare();
		for(int i = 0; i < terrain.width(); i ++)
			for(int j = 0; j < terrain.height(); j ++)
				renderer.render(terrain, i, j);
		renderer.cleanup();
	}
}
