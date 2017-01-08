package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.Coordinator;

public class TileOutline {
	private final double neX, neY, seX, seY, atX, atY;
	public TileOutline(double neX, double neY, double seX, double seY, double atX, double atY) {
		this.neX = neX;	this.neY = neY;
		this.seX = seX;	this.seY = seY;
		this.atX = atX; this.atY = atY;
	}
	
	public int elevation(Terrain terrain, int x, int y) {
		int z = terrain.elevation(x + 0, y + 0);
		
		int z1 = terrain.elevation(x - 1, y + 0);
		int z2 = terrain.elevation(x - 1, y - 1);
		int z3 = terrain.elevation(x + 0, y - 1);
		
		if(z1 == z + 1 || z2 == z + 1 || z3 == z + 1) z = z + 1;
		return z;
	}
	
	private void map(Coordinator coord, Terrain terrain, double x, double y, double z) throws LWJGLException {
		coord.coord(seX * x + neX * y + atX * z, seY * x + neY * y + atY * z);
	}
	
	public void left(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		map(coord, terrain, x, y, elevation(terrain, x, y));
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
	
	public void any(Coordinator coord, Terrain terrain, double x, double y, double z) throws LWJGLException {
		int xi = (int) Math.floor(x);
		int yi = (int) Math.floor(y);
		double xd = x - xi;	double yd = y - yi;
		int e00 = elevation(terrain, xi + 0, yi + 0);
		int e01 = elevation(terrain, xi + 0, yi + 1);
		int e11 = elevation(terrain, xi + 1, yi + 1);
		int e10 = elevation(terrain, xi + 1, yi + 0);
		
		double e0 = yd * e00 + (1 - yd) * e01;
		double e1 = yd * e10 + (1 - yd) * e11;
		double e = xd * e0 + (1 - xd) * e1;
		map(coord, terrain, x, y, e + z);
	}
}
