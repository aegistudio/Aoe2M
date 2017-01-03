package net.aegistudio.aoe2m.assetdba.blob;

import java.io.IOException;
import java.io.InputStream;

/**
 * A SLP texture is a blob of a big stitched image with
 * its subtexture described.
 * 
 * @author aegistudio
 */

public interface SlpTexture {
	public InputStream image() throws IOException;
	
	public SlpSubTexture[] subTextures();
}
