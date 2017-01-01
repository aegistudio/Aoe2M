package net.aegistudio.aoe2m.wyvern;

import java.io.IOException;
import java.util.TreeMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.asset.TileAssetManager;
import net.aegistudio.aoe2m.wyvern.render.BasicTextureManager;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.terrain.SimpleTerrain;
import net.aegistudio.aoe2m.wyvern.tile.BasicRenderer;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class BasicRender extends WyvernRider {
	public final TileAssetManager assetManager;
	public final TextureManager textureManager;
	public final TileOutline outline;
	public final BasicRenderer renderer;
	
	public BasicRender() throws IOException, LWJGLException {
		assetManager = new TileAssetManager(32);
		textureManager = new BasicTextureManager();
		outline = new TileOutline(49, 25, 49, -25, 0, 20);
		renderer = new BasicRenderer(assetManager, textureManager, outline);
	}
	
	public final SimpleTerrain terrain = new SimpleTerrain(10, 10); {
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
	
	public int width() { return  600; }
	public int height() { return 480; }

	public void prepare() throws LWJGLException {
		Keyboard.create();
		TextureBinding.instance.enable();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA);
	}
	
	int translateX = -400, translateY = 0;
	double scale = 400;
	TreeMap<Integer, Boolean> down = new TreeMap<>();
	
	public void render() throws LWJGLException {
		while(Keyboard.next()) {
			int key = Keyboard.getEventKey();
			down.put(key, Keyboard.isKeyDown(key));
		}
		
		if(down.getOrDefault(Keyboard.KEY_LEFT, false)) translateX -= 10;
		if(down.getOrDefault(Keyboard.KEY_RIGHT, false)) translateX += 10;
		if(down.getOrDefault(Keyboard.KEY_DOWN, false)) translateY -= 10;
		if(down.getOrDefault(Keyboard.KEY_UP, false)) translateY += 10;
		if(down.getOrDefault(Keyboard.KEY_LBRACKET, false)) scale -= 10;
		if(down.getOrDefault(Keyboard.KEY_RBRACKET, false)) scale += 10;
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glScaled(1./scale, 1./scale, 0);
		GL11.glTranslated(translateX, translateY, 0);
		
		GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);
		
		for(int i = 0; i < terrain.width(); i ++)
			for(int j = 0; j < terrain.height(); j ++)
				renderer.render(terrain, i, j);
	}
	
	public void dispose() throws LWJGLException {
		textureManager.destroy();
		Keyboard.destroy();
	}
}
