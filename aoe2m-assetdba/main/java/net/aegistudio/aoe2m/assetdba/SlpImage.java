package net.aegistudio.aoe2m.assetdba;

import java.awt.image.BufferedImage;

/**
 * A SLP texture is a blob of a big stitched image with
 * its subtexture described.
 * 
 * @author aegistudio
 */

public interface SlpImage {
	public int width();
	
	public int height();
	
	public BufferedImage image();
	
	public SlpSubImage[] subTextures();
}
