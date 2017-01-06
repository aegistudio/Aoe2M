package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.asset.StickyShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.tile.StickyRenderer;
import net.aegistudio.aoe2m.wyvern.tile.StickyTexture;

public class StickyDrawRender extends SelectRender {
	protected final StickyTexture stickyTexture;
	protected final StickyShaderProgram program;
	protected final StickyRenderer stickyRenderer;
	
	public StickyDrawRender() throws IOException, LWJGLException {
		super();
		program = new StickyShaderProgram();
		stickyTexture = new StickyTexture(program, 400, 400);
		stickyRenderer = new StickyRenderer(textureManager, 
				outline, stickyTexture, program);
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		program.create();
		textureManager.allocate(stickyTexture);
		stickyTexture.size(2, 3, 4, 4);
		
		stickyTexture.begin();
			GL11.glClearColor(0, 0, 0, 0);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stickyTexture.end();
	}
	
	public void processKey() {
		super.processKey();
	}
	
	public void render() throws LWJGLException {
		super.render();
		stickyRenderer.prepare();
		for(int i = 0; i < terrain.width(); i ++)
			for(int j = 0; j < terrain.height(); j ++)
				stickyRenderer.render(terrain, i, j);
		stickyRenderer.cleanup();
		
		if(super.down.getOrDefault(Keyboard.KEY_EQUALS, false)) {
			textureManager.bind(stickyTexture, TextureBinding.instance);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(0, 0);
				
				GL11.glTexCoord2d(1, 0);
				GL11.glVertex2d(1, 0);
	
				GL11.glTexCoord2d(1, 1);
				GL11.glVertex2d(1, 1);
				
				GL11.glTexCoord2d(0, 1);
				GL11.glVertex2d(0, 1);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}
	
	public void dispose() throws LWJGLException {
		super.dispose();
	}
	
	boolean previous = false; double px; double py;
	public void selectPos(double x, double y, double n) {
		if(previous) try {
			stickyTexture.begin();
				TextureBinding.instance.bind(0);
				GL11.glColor3d(1.0, 1.0, 1.0);
				GL11.glLineWidth(3.0f);
				GL11.glBegin(GL11.GL_LINES);
					GL11.glVertex2d(px, py);
					GL11.glVertex2d(x, y);
				GL11.glEnd();
			stickyTexture.end();
		} catch(LWJGLException exception) {
			exception.printStackTrace();
		}
		else previous = true;
		px = x; py = y;
	}
}