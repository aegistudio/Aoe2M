package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.asset.SelectShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.FrameRenderObject;
import net.aegistudio.aoe2m.wyvern.tile.SelectRenderer;
import net.aegistudio.aoe2m.wyvern.tile.TapSelection;

public class SelectRender extends BlendingRender {
	protected final SelectRenderer selection;
	protected final SelectShaderProgram program;
	protected final FrameRenderObject offlineFrame;
	
	public SelectRender() throws IOException, LWJGLException {
		super();
		program = new SelectShaderProgram();
		offlineFrame = new FrameRenderObject(300, 300, 4, GL11.GL_RGBA8, GL11.GL_RGBA,
				ARBFramebufferObject.GL_COLOR_ATTACHMENT0);
		selection = new SelectRenderer(outline, offlineFrame, program);
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		program.create();
		offlineFrame.create();
		Mouse.create();
	}
	
	public void processKey() {
		super.processKey();
	}
	
	public void render() throws LWJGLException {
		super.render();
		selection.prepare();
		for(int i = 0; i < terrain.width(); i ++)
			for(int j = 0; j < terrain.height(); j ++)
				selection.render(terrain, i, j);
		selection.cleanup();		
		
		while(Mouse.next()) {
				TapSelection tap = new TapSelection(offlineFrame) {
					@Override
					protected void select(double x, double y, double z) {
						System.out.println(x + " " + y + " " + z);
					}
				};
				tap.selectionTest(terrain, Mouse.getEventX(), Mouse.getEventY(), width(), height());
			}
		
		if(down.getOrDefault(Keyboard.KEY_TAB, false)) {
			ARBFramebufferObject.glBindFramebuffer(
					ARBFramebufferObject.GL_READ_FRAMEBUFFER, offlineFrame.fboid);
			ARBFramebufferObject.glBlitFramebuffer(0, 0, offlineFrame.width, offlineFrame.height, 
					0, 0, offlineFrame.width, offlineFrame.height, GL11.GL_COLOR_BUFFER_BIT, GL11.GL_NEAREST);
		}
	}
	
	public void dispose() throws LWJGLException {
		super.dispose();
		program.destroy();
		offlineFrame.destroy();
		Mouse.destroy();
	}
}
