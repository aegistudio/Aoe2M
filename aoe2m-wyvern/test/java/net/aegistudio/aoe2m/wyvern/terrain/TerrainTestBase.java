package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.IOException;
import java.util.TreeMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.WyvernRider;
import net.aegistudio.aoe2m.wyvern.asset.Blendomatic;
import net.aegistudio.aoe2m.wyvern.asset.TileAssetManager;
import net.aegistudio.aoe2m.wyvern.render.BasicTextureManager;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public abstract class TerrainTestBase extends WyvernRider {
	public final Blendomatic blendomatic;
	public final TileAssetManager assetManager;
	public final TextureManager textureManager;
	public final TileOutline outline;
	
	public TerrainTestBase() throws IOException, LWJGLException {
		blendomatic = new Blendomatic();
		assetManager = new TileAssetManager(blendomatic);
		textureManager = new BasicTextureManager();
		outline = new TileOutline(49, 25, 49, -25, 0, 20);
	}
	
	public int width() { return  600; }
	public int height() { return 480; }

	public void prepare() throws LWJGLException {
		Keyboard.create();
		TextureBinding.instance.enable();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA);
	}
	
	protected int translateX, translateY;
	protected double scale;
	
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
		
		renderTerrain();
	}
	
	protected abstract void renderTerrain() throws LWJGLException;
	
	public void dispose() throws LWJGLException {
		textureManager.destroy();
		Keyboard.destroy();
	}
}
