package net.aegistudio.aoe2m.wyvern.asset;

import java.io.IOException;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.blob.SlpImage;
import net.aegistudio.aoe2m.assetdba.terrain.BlendomaticManager;
import net.aegistudio.aoe2m.wyvern.render.ParentTexture;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;

public class Blendomatic {
	public final ParentTexture[] blendomatics;
	
	public Blendomatic(BlendomaticManager blendomaticManager) throws IOException {
		blendomatics = new ParentTexture[blendomaticManager.modes()];
		for(int i = 0; i < blendomatics.length; i ++) {
			SlpImage texture = blendomaticManager.query(i);
			if(texture == null) continue;
			blendomatics[i] = new SlpParentTexture(() -> texture);
		}
	}
	
	public ParentTexture getMaskTexture(int id) {
		if(id < 0 || id >= blendomatics.length)
			return null;
		else return blendomatics[id];
	}

	public static final int[] ADJACENCE_LOOKUP = new int[] {
			-1,		/** .... **/
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
	
	public static int getAdjacenceBlend(int mask, int x, int y) {
		int primary = ADJACENCE_LOOKUP[mask];
		if(primary <= 12) primary += ((x + y) % 4);
		return primary;
	}
	
	public static final int[] DIAGONAL_LOOKUP = new int[] {
			16, 17, 19, 18, 
	};
	
	public interface DiagonalBlendConsumer {
		public void consume(int mask) throws LWJGLException;
	}
	
	public static void getDiagonalBlend(int mask, DiagonalBlendConsumer consumer) throws LWJGLException {
		for(int i = 0; i < 4; i ++)
			if((mask & (1 << i)) != 0)
				consumer.consume(DIAGONAL_LOOKUP[i]);
	}
}
