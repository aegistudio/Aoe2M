package net.aegistudio.aoe2m.wyvern.unit;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.Terrain;

public interface GraphicsRenderer {
	public void prepare() throws LWJGLException;
	
	public void draw(Terrain terrain, double x, double y, double z, 
			int graphics, int frame, int angle) throws LWJGLException;
	
	public void cleanup() throws LWJGLException;
}
