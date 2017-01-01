package net.aegistudio.aoe2m.wyvern.render;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.nio.IntBuffer;
import java.util.function.Supplier;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;

/**
 * A GL texture is a standard of OpenGL texture.
 * 
 * @author aegistudio
 *
 */

public class GlTexture implements Texture {
	protected final Supplier<BufferedImage> imageSupplier;
	public GlTexture(BufferedImage image) {
		this(() -> image);
	}
	
	public GlTexture(Supplier<BufferedImage> image) {
		this.imageSupplier = image;
	}
	
	public void make(int texture) throws LWJGLException {
		BufferedImage image = imageSupplier.get();
		int width = image.getWidth();
		int height = image.getHeight();
		
		// Prime pixel data.		
		int totalPixel = width * height;
		IntBuffer buffer = BufferUtils.createIntBuffer(totalPixel);
		WritableRaster raster = image.getRaster();
		WritableRaster alphaRaster = image.getAlphaRaster();
		
		for(int j = 0; j < height; j ++)
			for(int i = 0; i < width; i ++) {
				int r = raster.getSample(i, j, 0) << (8 * 0);
				int g = raster.getSample(i, j, 1) << (8 * 1);
				int b = raster.getSample(i, j, 2) << (8 * 2);
				int a = (alphaRaster == null? 255 : alphaRaster.getSample(i, j, 0)) << (8 * 3);
				buffer.put(r | g | b | a);
			}
		buffer.flip();
		
		// Begin allocating.
		glBindTexture(GL_TEXTURE_2D, texture);
		
		// Texture parameters
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		
		// Post to Vram space.
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, 
				GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	}

	public void bottomLeft(Coordinator bind) throws LWJGLException {	bind.coord(0, 0);		}
	public void bottomRight(Coordinator bind) throws LWJGLException {	bind.coord(1, 0);		}
	public void topRight(Coordinator bind) throws LWJGLException {		bind.coord(1, 1);		}
	public void topLeft(Coordinator bind) throws LWJGLException {		bind.coord(0, 1);		}
	public void center(Coordinator bind) throws LWJGLException {		bind.coord(0.5, 0.5);	}
}