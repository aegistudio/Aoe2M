package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.render.Coordinator;
import net.aegistudio.aoe2m.wyvern.terrain.Terrain;

public class TileOutline {
	private final double neX, neY, seX, seY, atX, atY;
	public TileOutline(double neX, double neY, double seX, double seY, double atX, double atY) {
		this.neX = neX;	this.neY = neY;
		this.seX = seX;	this.seY = seY;
		this.atX = atX; this.atY = atY;
	}
	
	public void left(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		int z = terrain.elevation(x + 0, y + 0);
		
		int z1 = terrain.elevation(x - 1, y + 0);
		int z2 = terrain.elevation(x - 1, y - 1);
		int z3 = terrain.elevation(x + 0, y - 1);
		
		if(z1 == z + 1 || z2 == z + 1 || z3 == z + 1) z = z + 1;
		coord.coord(seX * x + neX * y + atX * z, seY * x + neY * y + atY * z);
	}
	
	public void top(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		left(coord, terrain, x + 0, y + 1);
	}
	
	public void bottom(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		left(coord, terrain, x + 1, y + 0);
	}
	
	public void right(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		left(coord, terrain, x + 1, y + 1);
	}
}
