package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.IOException;
import java.util.TreeMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.media.Storage;
import net.aegistudio.aoe2m.opnagedb.OpgAssetConnection;
import net.aegistudio.aoe2m.wyvern.WyvernRider;
import net.aegistudio.aoe2m.wyvern.asset.Blendomatic;
import net.aegistudio.aoe2m.wyvern.render.BasicTextureManager;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileBaseOutline;
import net.aegistudio.aoe2m.wyvern.tile.TileBiasOutline;
import net.aegistudio.aoe2m.wyvern.tile.TileMetaManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public abstract class TerrainTestBase extends WyvernRider {
	public final AssetConnection connection;
	public final Blendomatic blendomatic;
	public final TileMetaManager assetManager;
	public final TextureManager textureManager;
	public final TileOutline outline, biasOutline;
	
	public TerrainTestBase() throws IOException, LWJGLException {
		//Storage storage = new net.aegistudio.aoe2m.media.FileStorage(new File("assets"));
		Storage storage = new net.aegistudio.aoe2m.media.ZipStorage(
				new java.util.zip.ZipFile("asset.zip"), "converted");
		
		connection = new OpgAssetConnection(storage, new ConsoleAssetListener());
		blendomatic = new Blendomatic(connection);
		assetManager = new TileMetaManager(connection, blendomatic);
		textureManager = new BasicTextureManager();
		outline = new TileBaseOutline(49, 25, 49, -25, 0, 20);
		//biasOutline = new TileBiasOutline(outline, 3.96, 0, 0, 2);
		biasOutline = new TileBiasOutline(outline, 1.98, 0, 0, 1);
	}
	
	public int width() { return  600; }
	public int height() { return 480; }

	public void prepare() throws LWJGLException {
		Keyboard.create();
		TextureBinding.instance.enable();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, 
				GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	protected int translateX, translateY;
	protected double scale;
	
	protected TreeMap<Integer, Boolean> down = new TreeMap<>();
	
	public void render() throws LWJGLException {
		while(Keyboard.next()) {
			int key = Keyboard.getEventKey();
			down.put(key, Keyboard.isKeyDown(key));
		}
		processKey();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glScaled(1./scale, 1.21/scale, 0);
		GL11.glTranslated(translateX, translateY, 0);
		
		GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);
		
		renderTerrain();
	}
	
	protected void processKey() {
		if(down.getOrDefault(Keyboard.KEY_LEFT, false)) translateX -= 10;
		if(down.getOrDefault(Keyboard.KEY_RIGHT, false)) translateX += 10;
		if(down.getOrDefault(Keyboard.KEY_DOWN, false)) translateY -= 10;
		if(down.getOrDefault(Keyboard.KEY_UP, false)) translateY += 10;
		if(down.getOrDefault(Keyboard.KEY_LBRACKET, false)) scale -= 10;
		if(down.getOrDefault(Keyboard.KEY_RBRACKET, false)) scale += 10;		
	}
	
	protected abstract void renderTerrain() throws LWJGLException;
	
	public void dispose() throws LWJGLException {
		textureManager.destroy();
		Keyboard.destroy();
	}
}
