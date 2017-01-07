package net.aegistudio.aoe2m.wyvern;

public class SimpleTerrain implements Terrain {
	public final int width, height;
	public final byte[][] terrain;
	public final byte[][] elevation;
	public final boolean[][] selection;
	
	public SimpleTerrain(int width, int height) {
		this.width = width;
		this.height = height;
		this.terrain = new byte[width][height];
		this.elevation = new byte[width][height];
		this.selection = new boolean[width][height];
	}

	public int tile(int x, int y) {	
		if(x < 0 || x >= width) return -1;
		if(y < 0 || y >= height) return -1;
		return terrain[x][y];
	}
	
	public int elevation(int x, int y) {
		if(x < 0) x = 0;	if(x >= width) x = width - 1;
		if(y < 0) y = 0;	if(y >= height) y = height - 1;
		return elevation[x][y];
	}
	
	public boolean selected(int x, int y) { return selection[x][y];	}
	
	public int width() 					{	return width;			}
	public int height() 				{	return height;			}
}
