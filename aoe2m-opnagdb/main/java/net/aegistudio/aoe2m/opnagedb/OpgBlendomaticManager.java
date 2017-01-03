package net.aegistudio.aoe2m.opnagedb;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import net.aegistudio.aoe2m.assetdba.blob.SlpImage;
import net.aegistudio.aoe2m.assetdba.terrain.BlendomaticManager;

public class OpgBlendomaticManager implements BlendomaticManager {
	protected final SlpImage[] blendomatics;
	
	public OpgBlendomaticManager(File root) {
		File blendomatic = new File(root, "blendomatic");
		TreeMap<Integer, SlpImage> maps = new TreeMap<>();
		Arrays.stream(blendomatic.listFiles())
				.filter(file -> file.getName().endsWith(".docx"))
				.forEach(file -> { try {
					String name = file.getName();
					name = name.substring(0, name.length() - ".docx".length());
					int order = Integer.parseInt(name.substring("mode".length()));
					maps.put(order, new OpgSlpImage(blendomatic, name));
				} catch(IOException e) {}});
		
		int max = 0;
		for(int value : maps.keySet()) max = Math.max(max, value);
		blendomatics = new SlpImage[max + 1];
		
		for(Map.Entry<Integer, SlpImage> entry : maps.entrySet())
			blendomatics[entry.getKey()] = entry.getValue();
	}
	@Override
	public int modes() {
		return blendomatics.length;
	}

	@Override
	public SlpImage query(int mode) {
		return blendomatics[mode];
	}
}
