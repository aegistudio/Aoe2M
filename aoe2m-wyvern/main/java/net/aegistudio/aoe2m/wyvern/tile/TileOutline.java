package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.Coordinator;

public interface TileOutline {
	public void left(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException;
	
	public void top(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException;
	
	public void bottom(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException;
	
	public void right(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException;
	
	public double elevation(Terrain terrain, double x, double y) throws LWJGLException;
	
	public void any(Coordinator coord, Terrain terrain, double x, double y, double z) throws LWJGLException;
}
