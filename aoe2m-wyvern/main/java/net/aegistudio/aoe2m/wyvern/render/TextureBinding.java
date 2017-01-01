package net.aegistudio.aoe2m.wyvern.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

public class TextureBinding implements TextureManager.TextureBind, Texture.TextureCoord {
	public static final TextureBinding instance = new TextureBinding();
	private TextureBinding() {}
	
	public void enable() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public void bind(int index) throws LWJGLException {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, index);
	}

	public void coord(double u, double v) throws LWJGLException {
		GL11.glTexCoord2d(u, v);
	}
	
	public void disable() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
