package net.aegistudio.aoe2m.wyvern.asset;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.util.function.Supplier;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.PlayerPalette;
import static org.lwjgl.opengl.ARBShaderObjects.*;

public class PlayerPaletteBuffer {
	protected final Supplier<PlayerPalette> provider;
	public PlayerPaletteBuffer(Supplier<PlayerPalette> paletteProvider) {
		provider = paletteProvider;
	}

	FloatBuffer colorBuffer;
	public void create() throws LWJGLException {
		if(colorBuffer != null) return;
		
		PlayerPalette palette = provider.get();
		colorBuffer = BufferUtils.createFloatBuffer(palette.items.length * 4);
		for(int i = 0; i < palette.items.length; i ++) {
			Color colorVector = palette.items[i];
			colorBuffer.put(colorVector.getRed() / 256.0f);
			colorBuffer.put(colorVector.getGreen() / 256.0f);
			colorBuffer.put(colorVector.getBlue() / 256.0f);
			colorBuffer.put(colorVector.getAlpha() / 256.0f);
		}
		colorBuffer.flip();
	}
	
	public void set(int allMask, int subLength, int subMask, int items) {
		PlayerPalette palette = provider.get();
		glUniform1iARB(allMask, palette.allLength - 1);
		glUniform1iARB(subLength, palette.subLength);
		glUniform1iARB(subMask, palette.subLength - 1);
		glUniform4ARB(items, colorBuffer);
	}
	
	public void destroy() {
		colorBuffer = null;
	}
}
