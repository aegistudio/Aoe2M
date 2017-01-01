package net.aegistudio.aoe2m.wyvern.tile;

public class Blendomatic {
	public static final int[] LOOKUP = new int[] {
			+4,		/** ...X **/
			+0,		/** ..X. **/
			25, 	/** ..XX **/
			+8,	 	/** .X.. **/
			20, 	/** .X.X **/
			24, 	/** .XX. **/
			26, 	/** .XXX **/
			12, 	/** X... **/
			23,		/** X..X **/
			21,		/** X.X. **/
			29, 	/** X.XX **/
			22, 	/** XX.. **/
			28, 	/** XX.X **/
			27, 	/** XXX. **/
			30, 	/** XXXX **/
	};
	
	public int getAdjacenceBlend(int mask, int x, int y) {
		int primary = LOOKUP[mask];
		if(primary <= 12) primary += ((x + y) % 4);
		return primary;
	}
}
