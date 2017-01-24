package net.aegistudio.aoe2m.opnagedb;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.SlpImage;
import net.aegistudio.aoe2m.media.Storage;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;

public class OpgBlendomaticManager implements AssetManager<SlpImage> {
	protected final SlpImage[] blendomatics;
	
	public OpgBlendomaticManager(AssetListener perfLog, Storage root) throws IOException {
		Storage blendomatic = root.chdir("blendomatic");
		Storage[] blendomaticFiles = blendomatic.list();
		perfLog.initSubsystem(BLENDOMATIC_NAME, BLENDOMATIC_CLASS, blendomaticFiles.length);
		
		TreeMap<Integer, SlpImage> maps = new TreeMap<>();
		Arrays.stream(blendomaticFiles)
				.filter(file -> file.name().endsWith(".docx"))
				.forEach(file -> { try {
					String name = file.name();
					name = name.substring(0, name.length() - ".docx".length());
					int order = Integer.parseInt(name.substring("mode".length()));
					
					perfLog.initAsset(BLENDOMATIC_NAME, BLENDOMATIC_CLASS, order);
					maps.put(order, new OpgSlpImage(
							() -> perfLog.initArchive(BLENDOMATIC_NAME, BLENDOMATIC_CLASS, order),
							() -> perfLog.readyArchive(BLENDOMATIC_NAME, BLENDOMATIC_CLASS, order), 
							blendomatic, name));
					perfLog.readyAsset(BLENDOMATIC_NAME, BLENDOMATIC_CLASS, order);
				} catch(IOException e) {}});
		
		int max = 0;
		for(int value : maps.keySet()) max = Math.max(max, value);
		blendomatics = new SlpImage[max + 1];
		
		for(Map.Entry<Integer, SlpImage> entry : maps.entrySet())
			blendomatics[entry.getKey()] = entry.getValue();
		
		perfLog.readySubsystem(BLENDOMATIC_NAME, BLENDOMATIC_CLASS);
	}

	@Override
	public SlpImage query(int mode) {
		return blendomatics[mode];
	}
	@Override
	public void iterate(BiConsumer<Integer, SlpImage> iterator) {
		for(int i = 0; i < blendomatics.length; i ++)
			if(blendomatics[i] != null)
				iterator.accept(i, blendomatics[i]);
	}

	@Override
	public int max() {
		return blendomatics.length;
	}
}
