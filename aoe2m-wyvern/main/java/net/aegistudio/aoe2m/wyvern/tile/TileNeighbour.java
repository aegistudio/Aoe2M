package net.aegistudio.aoe2m.wyvern.tile;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum TileNeighbour {
	NORTH			(-1, +1, false),
	NORTH_EAST		( 0, +1, true),
	EAST			(+1, +1, false),
	SOUTH_EAST		(+1,  0, true),
	SOUTH			(+1, -1, false),
	SOUTH_WEST		( 0, -1, true),
	WEST			(-1, -1, false),
	NORTH_WEST		(-1,  0, true);
	
	public final int offsetX, offsetY;
	public final boolean adjacent;
	
	private TileNeighbour(int offsetX, int offsetY, boolean adjacent) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.adjacent = adjacent;
	}
	
	public <T> T access(int x, int y, BiFunction<Integer, Integer, T> accessor) {
		return accessor.apply(x + offsetX, y + offsetY);
	}
	
	public static TileNeighbour[] byAdjacence(boolean adjacent) {
		return Arrays.stream(values())
				.filter(tile -> tile.adjacent == adjacent)
				.toArray(TileNeighbour[]::new);
	}
	
	public static TileNeighbour[] adjacents() {		return byAdjacence(true);	}
	public static TileNeighbour[] diagnonals() {	return byAdjacence(false);	}
}
