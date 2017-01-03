package net.aegistudio.aoe2m.opnagedb;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import net.aegistudio.aoe2m.assetdba.blob.SlpTexture;
import net.aegistudio.aoe2m.assetdba.terrain.BlendomaticManager;

public class OpgBlendomaticManager implements BlendomaticManager {
	protected final SlpTexture[] blendomatics;
	
	public OpgBlendomaticManager(File root) {
		File blendomatic = new File(root, "blendomatic");
		TreeMap<Integer, SlpTexture> maps = new TreeMap<>();
		Arrays.stream(blendomatic.listFiles())
				.filter(file -> file.getName().endsWith(".docx"))
				.forEach(file -> { try {
					String name = file.getName();
					name = name.substring(0, name.length() - ".docx".length());
					int order = Integer.parseInt(name.substring("mode".length()));
					maps.put(order, new OpgSlpTexture(blendomatic, name));
				} catch(IOException e) {}});
		
		int max = 0;
		for(int value : maps.keySet()) max = Math.max(max, value);
		blendomatics = new SlpTexture[max + 1];
		
		for(Map.Entry<Integer, SlpTexture> entry : maps.entrySet())
			blendomatics[entry.getKey()] = entry.getValue();
	}
	@Override
	public int modes() {
		return blendomatics.length;
	}

	@Override
	public SlpTexture query(int mode) {
		return blendomatics[mode];
	}
}
