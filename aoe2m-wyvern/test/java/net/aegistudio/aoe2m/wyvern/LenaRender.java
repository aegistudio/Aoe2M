package net.aegistudio.aoe2m.wyvern;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.render.BasicTextureManager;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.GlTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;

public class LenaRender extends WyvernRider {
	BufferedImage lena;
	GlTexture texture;	int id;
	TextureManager manager = new BasicTextureManager();
	TextureBinding texture2d = TextureBinding.instance;
	
	public LenaRender() throws IOException {
		lena = ImageIO.read(getClass().getResourceAsStream("/lena.png"));
		texture = new GlTexture(lena);
	}
	
	public int width() { return lena.getWidth(); }
	public int height() { return lena.getHeight(); }
	public String title() { return "Lena"; }

	public void prepare() throws LWJGLException {
		texture2d.enable();
		manager.allocate(texture);
	}
	
	public void render() throws LWJGLException {
		manager.bind(texture, texture2d::bind);
		
		GL11.glBegin(GL11.GL_QUADS);
			texture.topLeft(texture2d::coord); 		GL11.glVertex2f(-1, -1);
			texture.topRight(texture2d::coord); 	GL11.glVertex2f(+1, -1);
			texture.bottomRight(texture2d::coord); 	GL11.glVertex2f(+1, +1);
			texture.bottomLeft(texture2d::coord); 	GL11.glVertex2f(-1, +1);
		GL11.glEnd();
	}
	
	public void dispose() throws LWJGLException {
		manager.destroy();
	}
}
