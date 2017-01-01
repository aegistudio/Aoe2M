package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.terrain.Terrain;

public interface TileRenderer {
	public void prepare() throws LWJGLException;
	
	public void render(Terrain terrain, int x, int y) throws LWJGLException;
}
