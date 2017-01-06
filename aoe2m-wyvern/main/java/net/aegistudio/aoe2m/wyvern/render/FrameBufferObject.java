package net.aegistudio.aoe2m.wyvern.render;

import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;

/**
 * A commonly used server side buffer object
 * for offline rendering.
 * 
 * @author aegistudio
 */

public abstract class FrameBufferObject {
	public final int width, height;
	protected int fboid = 0;
	
	public FrameBufferObject(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void bind(int binding) {
		glBindFramebuffer(binding, fboid);
	}
	
	public static void unbind(int binding) {
		glBindFramebuffer(binding, 0);
	}
	
	protected abstract void subcreate() throws LWJGLException;
	
	protected abstract void subattach() throws LWJGLException;
	
	protected abstract void subdestroy() throws LWJGLException;
	
	public void create() throws LWJGLException {
		if(fboid > 0) return;
		subcreate();

		fboid = glGenFramebuffers();
		bind(GL_FRAMEBUFFER);
		subattach();
		if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
			throw new LWJGLException("frameBufferObject.imcomplete");
		unbind(GL_FRAMEBUFFER);
	}
	
	public void destroy() throws LWJGLException {
		if(fboid == 0) return;
		subdestroy();
		glDeleteFramebuffers(fboid);
		fboid = 0;
	}
	
	public void begin() {
		glPushAttrib(GL_ALL_ATTRIB_BITS);
		bind(GL_FRAMEBUFFER);
		glViewport(0, 0, width, height);
	}
	
	public void end() {
		unbind(GL_FRAMEBUFFER);
		glPopAttrib();
	}
}
