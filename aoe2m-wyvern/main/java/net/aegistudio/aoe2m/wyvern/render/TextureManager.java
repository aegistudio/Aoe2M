package net.aegistudio.aoe2m.wyvern.render;

import org.lwjgl.LWJGLException;

/**
 * Manage allocation of textures.
 * 
 * @author aegistudio
 */

public interface TextureManager {
	public interface TextureBind { public void bind(int index) throws LWJGLException; }
	
	public void allocate(Texture texture) throws LWJGLException;
	
	public void bind(Texture texture, TextureBind bind) throws LWJGLException;
	
	public void destroy() throws LWJGLException;
}