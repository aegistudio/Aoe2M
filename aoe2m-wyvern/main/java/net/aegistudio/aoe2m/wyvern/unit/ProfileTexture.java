package net.aegistudio.aoe2m.wyvern.unit;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ARBFramebufferObject;

import net.aegistudio.aoe2m.wyvern.render.FrameRenderObject;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;

public class ProfileTexture extends TextureBufferObject {
	public final FrameRenderObject depthComponent;
	
	public ProfileTexture(int width, int height) {
		super(width, height, GL_UNSIGNED_BYTE, 
				GL_RGBA, GL_RGBA, ARBFramebufferObject.GL_COLOR_ATTACHMENT0);
		depthComponent = new FrameRenderObject(width, height, GL_FLOAT, 
				GL_DEPTH_COMPONENT, GL_DEPTH_COMPONENT, 
				ARBFramebufferObject.GL_DEPTH_ATTACHMENT);
	}
	
	public void subcreate() throws LWJGLException {
		super.subcreate();
		depthComponent.subcreate();
	}
	
	public void subattach() throws LWJGLException {
		super.subattach();
		depthComponent.subattach();
	}
	
	public void subdestroy() throws LWJGLException {
		super.subdestroy();
		depthComponent.subdestroy();
	}
}
