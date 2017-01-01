package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.render.Coordinator;

public class TileOutline {
	private final double neX, neY, seX, seY;
	public TileOutline(double neX, double neY, double seX, double seY) {
		this.neX = neX;	this.neY = neY;
		this.seX = seX;	this.seY = seY;
	}
	
	public void left(Coordinator coord, int x, int y) throws LWJGLException {
		coord.coord(seX * x + neX * y, seY * x + neY * y);
	}
	
	public void top(Coordinator coord, int x, int y) throws LWJGLException {
		left(coord, x + 0, y + 1);
	}
	
	public void bottom(Coordinator coord, int x, int y) throws LWJGLException {
		left(coord, x + 1, y + 0);
	}
	
	public void right(Coordinator coord, int x, int y) throws LWJGLException {
		left(coord, x + 1, y + 1);
	}
	
	public void bottomLeft(Coordinator coord, int x, int y) throws LWJGLException {
		left((u, v) -> coord.coord(u, v + seY), x, y);
	}
	
	public void topLeft(Coordinator coord, int x, int y) throws LWJGLException {
		left((u, v) -> coord.coord(u, v + neY), x, y);
	}
	
	public void bottomRight(Coordinator coord, int x, int y) throws LWJGLException {
		right((u, v) -> coord.coord(u, v + seY), x, y);
	}
	
	public void topRight(Coordinator coord, int x, int y) throws LWJGLException {
		right((u, v) -> coord.coord(u, v + neY), x, y);
	}
}
