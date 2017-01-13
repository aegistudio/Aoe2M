package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.Coordinator;

public class TileBiasOutline implements TileOutline {
	private final TileOutline wrap;
	private final double bxX, bxY, byX, byY;
	public TileBiasOutline(TileOutline wrap, 
			double bxX, double bxY, double byX, double byY) {
		this.wrap = wrap;
		this.bxX = bxX;		this.bxY = bxY;
		this.byX = byX;		this.byY = byY;
	}
	
	@Override
	public void left(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		wrap.left((u, v) -> coord.coord(u - bxX, v - bxY), terrain, x, y);
	}

	@Override
	public void top(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		wrap.top((u, v) -> coord.coord(u + byX, v + byY), terrain, x, y);
	}

	@Override
	public void bottom(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		wrap.bottom((u, v) -> coord.coord(u - byX, v - byY), terrain, x, y);
	}

	@Override
	public void right(Coordinator coord, Terrain terrain, int x, int y) throws LWJGLException {
		wrap.right((u, v) -> coord.coord(u + bxX, v + bxY), terrain, x, y);
	}

	@Override
	public double elevation(Terrain terrain, double x, double y) throws LWJGLException {
		return wrap.elevation(terrain, x, y);
	}

	@Override
	public void any(Coordinator coord, Terrain terrain, double x, double y, double z) throws LWJGLException {
		wrap.any(coord, terrain, x, y, z);
	}
}
