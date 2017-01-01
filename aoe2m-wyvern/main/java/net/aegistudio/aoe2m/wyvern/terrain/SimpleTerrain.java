package net.aegistudio.aoe2m.wyvern.terrain;

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

	public int tile(int x, int y) 		{	return terrain[x][y];	}
	public int elevation(int x, int y) 	{	return elevation[x][y];	}
	public boolean selected(int x, int y) { return selection[x][y];	}
	
	public int width() 					{	return width;			}
	public int height() 				{	return height;			}
}
