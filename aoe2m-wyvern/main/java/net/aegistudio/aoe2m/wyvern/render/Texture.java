package net.aegistudio.aoe2m.wyvern.render;

import org.lwjgl.LWJGLException;

public interface Texture {
	public void make(int id) throws LWJGLException;

	public void bottomLeft(Coordinator bind) throws LWJGLException;

	public void bottomRight(Coordinator bind) throws LWJGLException;

	public void topRight(Coordinator bind) throws LWJGLException;
	
	public void topLeft(Coordinator bind) throws LWJGLException;
	
	public void center(Coordinator bind) throws LWJGLException;
}
