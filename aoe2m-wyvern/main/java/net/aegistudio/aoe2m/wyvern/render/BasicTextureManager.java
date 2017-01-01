package net.aegistudio.aoe2m.wyvern.render;

import java.util.HashMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

public class BasicTextureManager implements TextureManager {
	protected HashMap<Texture, Integer> map = new HashMap<>();

	@Override
	public void allocate(Texture texture) throws LWJGLException {
		if(map.containsKey(texture)) return;
		int newTexture = GL11.glGenTextures();
		texture.make(newTexture);
		map.put(texture, newTexture);
	}

	@Override
	public void bind(Texture texture, TextureBind bind) throws LWJGLException {
		allocate(texture);
		bind.bind(map.get(texture));
	}

	@Override
	public void destroy() throws LWJGLException {
		for(int id : map.values()) GL11.glDeleteTextures(id);
	}
}
