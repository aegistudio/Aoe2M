package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.opengl.ARBFramebufferObject;
import static org.lwjgl.opengl.GL11.*;

import net.aegistudio.aoe2m.wyvern.asset.StickyShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;

public class StickyTexture extends TextureBufferObject {
	protected float x, y, w, h;
	protected final StickyShaderProgram program;
	public StickyTexture(StickyShaderProgram program, int width, int height) {
		super(width, height, GL_BYTE, GL_RGBA, GL_RGBA,
				ARBFramebufferObject.GL_COLOR_ATTACHMENT0);
		this.program = program;
		x = 0; y = 0; w = width; h = height;
	}
	
	public void size(float x, float y, float w, float h) {
		this.x = x;		this.w = w;
		this.y = y;		this.h = h;
	}
	
	public void param() {
		program.size(x, y, w, h);
	}
	
	/**
	 * Projection Matrix: P
	 * 1. Affine transform.
	 * 2. P * (x + 0, y + 0) = (-1, -1)
	 * 3. P * (x + w, y + h) = (+1, +1)
	 * 
	 * Tr1: translate (-x, -y).
	 * Tr2: scale (2/w, 2/h).
	 * Tr4: translate (-1, -1)
	 */
	
	public void begin() {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glLoadIdentity();
		glTranslated(-1, -1, 0);
		glScaled(2./w, 2./h, 0);
		glTranslated(-x, -y, 0);
		
		super.begin();
	}
	
	public void end() {
		super.end();

		glMatrixMode(GL_PROJECTION);
		glPopMatrix();
	}
}
