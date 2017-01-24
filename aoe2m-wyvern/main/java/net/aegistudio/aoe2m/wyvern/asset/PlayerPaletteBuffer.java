package net.aegistudio.aoe2m.wyvern.asset;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GLContext;

import net.aegistudio.aoe2m.assetdba.PlayerPalette;
import static org.lwjgl.opengl.ARBBufferObject.*;
import static org.lwjgl.opengl.ARBUniformBufferObject.*;

public class PlayerPaletteBuffer {
	protected final Supplier<PlayerPalette> provider;
	public PlayerPaletteBuffer(Supplier<PlayerPalette> paletteProvider) {
		provider = paletteProvider;
	}

	protected int id = -1;
	public void create() throws LWJGLException {
		if(!GLContext.getCapabilities().GL_ARB_uniform_buffer_object)
			throw new LWJGLException("uniformBufferObject.unsupported");
		
		if(id >= 0) return;
		
		// Prime data input byte array.
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(byteOutput);
		try {
			PlayerPalette palette = provider.get();
			dataOutput.writeInt(palette.allLength - 1);
			dataOutput.writeInt(palette.subLength);
			dataOutput.writeInt(palette.subLength - 1);
			
			for(int i = 0; i < palette.items.length; i ++) {
				Color colorVector = palette.items[i];
				dataOutput.writeFloat(colorVector.getRed() / 256.0f);
				dataOutput.writeFloat(colorVector.getGreen() / 256.0f);
				dataOutput.writeFloat(colorVector.getBlue() / 256.0f);
				dataOutput.writeFloat(colorVector.getAlpha() / 256.0f);
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		
		// Push into byte buffer.
		byte[] bytes = byteOutput.toByteArray();
		ByteBuffer byteBuffer = BufferUtils.createByteBuffer(bytes.length);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		
		// Server side communication.
		id = glGenBuffersARB();
		glBindBufferARB(GL_UNIFORM_BUFFER, id);
		glBufferDataARB(GL_UNIFORM_BUFFER, 
				byteBuffer, GL_STATIC_DRAW_ARB);
	}
	
	public void set(int location) {
		glBindBufferBase(GL_UNIFORM_BUFFER, location, 0);
	}
	
	public void destroy() {
		if(id < 0) return;
		glDeleteBuffersARB(id);
		id = -1;
	}
}
