package net.aegistudio.aoe2m.wyvern.asset;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import net.aegistudio.aoe2m.wyvern.render.ParentTexture;

public class Blendomatic {
	public final ParentTexture[] blendomatics;
	
	public Blendomatic() {
		File blendomatic = new File(new File("assets"), "blendomatic");
		TreeMap<Integer, ParentTexture> maps = new TreeMap<>();
		Arrays.stream(blendomatic.listFiles())
				.filter(file -> file.getName().endsWith(".docx"))
				.forEach(file -> { try {
					String name = file.getName();
					name = name.substring(0, name.length() - ".docx".length());
					int order = Integer.parseInt(name.substring("mode".length()));
					SlpTextureAsset asset = new SlpTextureAsset(blendomatic, name);
					maps.put(order, asset.toTexture());
				} catch(IOException e) {}});
		
		int max = 0;
		for(int value : maps.keySet()) max = Math.max(max, value);
		blendomatics = new ParentTexture[max + 1];
		
		for(Map.Entry<Integer, ParentTexture> entry : maps.entrySet())
			blendomatics[entry.getKey()] = entry.getValue();
	}
	
	public ParentTexture getMaskTexture(int id) {
		if(id < 0 || id >= blendomatics.length)
			return null;
		else return blendomatics[id];
	}

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
