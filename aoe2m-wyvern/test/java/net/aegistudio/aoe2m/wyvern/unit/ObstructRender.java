package net.aegistudio.aoe2m.wyvern.unit;

import java.io.IOException;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.asset.ObstructShaderProgram;

public class ObstructRender extends PlayerColorRender {
	public final ObstructShaderProgram obstructProgram;
	public final ObstructRenderer obstructRenderer;
	
	public ObstructRender() throws IOException, LWJGLException {
		super();
		obstructProgram = new ObstructShaderProgram(paletteBuffer);
		obstructRenderer = new ObstructRenderer(obstructProgram, connection::playerPalette, paletteHint, 
				profileMap, arbitrator, graphicsManager, biasOutline, textureManager);
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		obstructProgram.create();
	}
	
	public void render() throws LWJGLException {
		super.render();
		placement.render(obstructRenderer, terrain);
	}
	
	public void dispose() throws LWJGLException {
		super.dispose();
		obstructProgram.destroy();
	}
}
