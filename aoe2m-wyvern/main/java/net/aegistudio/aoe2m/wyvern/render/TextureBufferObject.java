package net.aegistudio.aoe2m.wyvern.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.ARBFramebufferObject.*;

/**
 * <p>A texture buffer object. </p>
 * 
 * <p><b>Warning: </b>You ought to allocate it in texture manager, before
 * creating the frame buffer object.</p>
 * 
 * @author aegistudio
 */

public class TextureBufferObject extends FrameBufferObject implements Texture {
	public final int type, format, internalFormat;
	public int tboid = 0, attachment;
	public TextureBufferObject(int width, int height, int type,
			int format, int internalFormat, int attachment) {
		super(width, height);
		this.type = type;
		this.format = format;
		this.internalFormat = internalFormat;
		this.attachment = attachment;
	}

	public void subcreate() throws LWJGLException {
		if(tboid == 0) 
			throw new LWJGLException("textureBufferObject.notAllocated");
	}
	
	public void subattach() throws LWJGLException {
		glFramebufferTexture2D(GL_DRAW_FRAMEBUFFER,
				attachment, GL_TEXTURE_2D, tboid, 0);
	}
	
	public void subdestroy() throws LWJGLException {	}

	@Override
	public void make(int id) throws LWJGLException {
		tboid = id;
		glBindTexture(GL_TEXTURE_2D, tboid);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		
		glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, 
				super.width, super.height, 0, format, type, (ByteBuffer)null);
		
		if(GL11.glGetError() != GL11.GL_NO_ERROR)
			throw new LWJGLException("textureBufferObject.createFailure");
		
		super.create();
	}
	
	public void bottomLeft(Coordinator bind) throws LWJGLException {	bind.coord(0., 0.);	}
	public void bottomRight(Coordinator bind) throws LWJGLException {	bind.coord(1., 0.);	}
	public void topRight(Coordinator bind) throws LWJGLException {		bind.coord(1., 1.);	}
	public void topLeft(Coordinator bind) throws LWJGLException {		bind.coord(0., 1.);	}
	public void center(Coordinator bind) throws LWJGLException {		bind.coord(.5, .5);	}

	@Override
	public void destroy(int id) throws LWJGLException {		super.destroy();	}
}
