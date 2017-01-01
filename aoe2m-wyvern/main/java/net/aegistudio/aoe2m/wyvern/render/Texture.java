package net.aegistudio.aoe2m.wyvern.render;

import org.lwjgl.LWJGLException;

public interface Texture {
	public interface TextureCoord { public void coord(double u, double v) throws LWJGLException; }
	
	public void make(int id) throws LWJGLException;

	public void bottomLeft(TextureCoord bind) throws LWJGLException;

	public void bottomRight(TextureCoord bind) throws LWJGLException;

	public void topRight(TextureCoord bind) throws LWJGLException;
	
	public void topLeft(TextureCoord bind) throws LWJGLException;
	
	public void center(TextureCoord bind) throws LWJGLException;	
}
