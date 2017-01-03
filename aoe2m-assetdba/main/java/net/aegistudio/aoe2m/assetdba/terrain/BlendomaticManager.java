package net.aegistudio.aoe2m.assetdba.terrain;

import net.aegistudio.aoe2m.assetdba.blob.SlpTexture;

public interface BlendomaticManager {
	public int modes();
	
	public SlpTexture query(int mode);
}
