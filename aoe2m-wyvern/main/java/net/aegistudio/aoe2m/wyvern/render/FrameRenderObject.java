package net.aegistudio.aoe2m.wyvern.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBFramebufferObject.*;

/**
 * A frame render object is a simplified
 * form of FBO + RBO usage. Where you only
 * render to a single attachment.
 * 
 * @author aegistudio
 */

public class FrameRenderObject {
	public final int width, height;
	public final int stride, format, internalFormat;
	public int fboid = 0, rboid = 0, attachment;
	
	public FrameRenderObject(int w, int h, int stride, int internalFormat, int format, int attachment) {
		this.width = w;
		this.height = h;
		this.stride = stride;
		this.format = format;
		this.internalFormat = internalFormat;
		this.attachment = attachment;
	}
	
	public void create() throws LWJGLException {
		if(rboid > 0 && fboid > 0) return;
		
		rboid = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, rboid);
		glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, width, height);
		if(glGetError() != GL11.GL_NO_ERROR)
			throw new LWJGLException("frameBufferObject.renderObjectError");
		glBindRenderbuffer(GL_RENDERBUFFER, 0);
		
		fboid = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, fboid);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, attachment, 
				GL_RENDERBUFFER, rboid);
		if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
			throw new LWJGLException("frameBufferObject.imcomplete");
		
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}
	
	public void begin() {
		glPushAttrib(GL_ALL_ATTRIB_BITS);
		glBindFramebuffer(GL_FRAMEBUFFER, fboid);
		glViewport(0, 0, width, height);
	}
	
	public void end() {
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glPopAttrib();
	}
	
	public void destroy() throws LWJGLException {
		glDeleteRenderbuffers(rboid);
		glDeleteFramebuffers(fboid);
		rboid = 0; fboid = 0;
	}
	
	public float[][][] download(int x, int y, int w, int h) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(w * h * stride);
		download(x, y, w, h, buffer);
		float[][][] result = new float[w][h][stride];
		for(int i = 0; i < w; i ++)
			for(int j = 0; j < h; j ++)
				for(int k = 0; k < stride; k ++)
					result[i][j][k] = buffer.get();
		return result;
	}
	
	public void download(int x, int y, int w, int h, FloatBuffer floatBuffer) {
		glBindFramebuffer(GL_READ_FRAMEBUFFER, fboid);
		glReadPixels(x, y, w, h, format, GL_FLOAT, floatBuffer);
		glBindFramebuffer(GL_READ_FRAMEBUFFER, 0);
	}
}
