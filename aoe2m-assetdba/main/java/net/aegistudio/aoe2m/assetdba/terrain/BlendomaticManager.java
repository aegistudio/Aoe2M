package net.aegistudio.aoe2m.assetdba.terrain;

import net.aegistudio.aoe2m.assetdba.blob.SlpImage;

public interface BlendomaticManager {
	public int modes();
	
	public SlpImage query(int mode);
}
