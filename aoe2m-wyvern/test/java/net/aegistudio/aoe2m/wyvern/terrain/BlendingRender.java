package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import net.aegistudio.aoe2m.wyvern.asset.BlendShaderProgram;
import net.aegistudio.aoe2m.wyvern.tile.BasicRenderer;
import net.aegistudio.aoe2m.wyvern.tile.BlendRenderer;

public class BlendingRender extends TerrainTestBase {
	public final BasicRenderer basicRenderer;
	public final BlendShaderProgram blendProgram;
	public final BlendRenderer blendRenderer;
	public final SimpleTerrain terrain = new SimpleTerrain(10, 10);
	public BlendingRender() throws IOException, LWJGLException {
		super();
		basicRenderer = new BasicRenderer(assetManager, textureManager, outline);
		blendProgram = new BlendShaderProgram();
		blendRenderer = new BlendRenderer(assetManager, textureManager, outline, blendProgram);

		terrain.terrain[4][4] = 2;
		terrain.terrain[4][5] = 2;
		terrain.terrain[4][6] = 2;
		
		terrain.terrain[5][4] = 2;
		terrain.terrain[5][5] = 4;
		terrain.terrain[5][6] = 2;
		
		terrain.terrain[6][4] = 2;
		terrain.terrain[6][5] = 2;
		terrain.terrain[6][6] = 2;		
		
		terrain.elevation[3][3] = 1;
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		super.translateX = -400;
		super.translateY = 0;
		super.scale = 400;
		
		blendProgram.create();
	}
	
	boolean blend = true;
	
	@Override
	protected void renderTerrain() throws LWJGLException {
		basicRenderer.prepare();
		for(int i = 0; i < terrain.width(); i ++)
			for(int j = 0; j < terrain.height(); j ++)
				basicRenderer.render(terrain, i, j);
		basicRenderer.cleanup();
		
		if(blend) {
			blendRenderer.prepare();
			for(int i = 0; i < terrain.width(); i ++)
				for(int j = 0; j < terrain.height(); j ++)
					blendRenderer.render(terrain, i, j);
			blendRenderer.cleanup();
		}
	}
	
	public void dispose() throws LWJGLException {
		blendProgram.destroy();
		super.dispose();
	}
	
	public void processKey() {
		super.processKey();
		blend = ! super.down.getOrDefault(Keyboard.KEY_SPACE, false);
	}
}
