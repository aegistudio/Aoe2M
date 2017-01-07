package net.aegistudio.aoe2m.wyvern.tile;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;

import net.aegistudio.aoe2m.wyvern.Terrain;

public enum TileNeighbour {
	EAST			(+1, +1, false,	0),
	SOUTH_EAST		(+1,  0, true,	0),
	NORTH			(-1, +1, false,	1),
	NORTH_EAST		( 0, +1, true,	1),
	WEST			(-1, -1, false,	2),
	NORTH_WEST		(-1,  0, true,	2),
	SOUTH			(+1, -1, false,	3),
	SOUTH_WEST		( 0, -1, true,	3);
	
	public final int offsetX, offsetY;
	public final boolean adjacent;
	public final int innerSetBit;
	
	private TileNeighbour(int offsetX, int offsetY, boolean adjacent, int innerSetIndex) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.adjacent = adjacent;
		
		innerSetBit = 1 << innerSetIndex;
	}
	
	public <T> T access(int x, int y, BiFunction<Integer, Integer, T> accessor) {
		return accessor.apply(x + offsetX, y + offsetY);
	}
	
	public TileNeighbour next(int count) {
		return values()[(ordinal() + count) % values().length];
	}
	
	private void setTileBit(int x, int y, Terrain terrain, Map<Integer, Integer> bitmap) {
		int tile = access(x, y, terrain::tile);
		if(tile < 0) return;
		bitmap.put(tile, bitmap.getOrDefault(tile, 0) | innerSetBit);	
	}
	
	public static void adjacentBits(int x, int y, Terrain terrain, Map<Integer, Integer> bitmap) {
		Arrays.stream(values())
			.filter(item -> item.adjacent)
			.forEach(item -> item.setTileBit(x, y, terrain, bitmap));
	}
	
	private int getAdjacence(int x, int y, Terrain terrain, Map<Integer, Integer> adjacentBitmap) {
		return adjacentBitmap.getOrDefault(access(x, y, terrain::tile), 0);
	}
	
	public static void diagonalBits(int x, int y, Terrain terrain, Map<Integer, Integer> bitmap, Map<Integer, Integer> adjacentBitmap) {
		Arrays.stream(values())
			.filter(item -> !item.adjacent)
			.filter(item -> (item.next(1 + 1).innerSetBit & item.getAdjacence(x, y, terrain, adjacentBitmap)) == 0)
			.filter(item -> (item.next(1 - 1).innerSetBit & item.getAdjacence(x, y, terrain, adjacentBitmap)) == 0)
			.forEach(item -> item.setTileBit(x, y, terrain, bitmap));
	}
}
