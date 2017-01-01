package net.aegistudio.aoe2m.wyvern;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.render.BasicTextureManager;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;

public class SlpFarmRender extends WyvernRider {
	SlpParentTexture texture; int id;
	TextureManager manager = new BasicTextureManager();
	TextureBinding texture2d = TextureBinding.instance;
	
	public SlpFarmRender() throws IOException, LWJGLException {
		BufferedImage farm = ImageIO.read(getClass().getResourceAsStream("/farm.png"));
		texture = new SlpParentTexture(() -> farm, getClass().getResourceAsStream("/farm.slp"));
	}
	
	public int width() { return  60 * 4; }
	public int height() { return 48 * 4; }
	public String title() { return "Farm - " + current(); }


	public void prepare() throws LWJGLException {
		texture2d.enable();
		manager.allocate(texture);
	}
	
	int counter = 0;
	private int current() {
		return (counter / 60) % texture.count();		
	}
	
	private void next() {
		counter ++;
	}
	
	public void render() throws LWJGLException {
		int nindex = current();
		
		manager.bind(texture, texture2d::bind);
		
		GL11.glBegin(GL11.GL_QUADS);
			texture.get(nindex).topLeft(texture2d::coord); 		GL11.glVertex2f(-1, -1);
			texture.get(nindex).topRight(texture2d::coord); 	GL11.glVertex2f(+1, -1);
			texture.get(nindex).bottomRight(texture2d::coord); 	GL11.glVertex2f(+1, +1);
			texture.get(nindex).bottomLeft(texture2d::coord); 	GL11.glVertex2f(-1, +1);
		GL11.glEnd();
		
		next();
	}
	
	public void dispose() throws LWJGLException {
		GL11.glDeleteTextures(id);
	}
}
