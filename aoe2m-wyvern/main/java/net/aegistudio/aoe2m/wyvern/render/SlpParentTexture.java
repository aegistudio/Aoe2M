package net.aegistudio.aoe2m.wyvern.render;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.SlpImage;

public class SlpParentTexture implements ParentTexture {
	protected final SlpImage image;
	protected final GlTexture texture;
	protected SlpTexture[] subTextures;
	
	public SlpParentTexture(SlpImage image) {
		this.image = image;
		this.texture = new GlTexture(this::getImage);
	}
	
	public BufferedImage getImage() {
		return image.image();
	}
	
	public void make(int id) throws LWJGLException {
		texture.make(id);
		
		BufferedImage bimage = getImage();
		int width = bimage.getWidth();
		int height = bimage.getHeight();
		
		this.subTextures = Arrays.stream(image.subTextures()).map(subTexture -> {
			if(subTexture == null) return null;
			
			double x = subTexture.x;	double left = 		x / width;
			double y = subTexture.y;	double bottom = 	y / height;
			double w = subTexture.w;	double right =	 	(x + w) / width;
			double h = subTexture.h;	double top = 		(y + h) / height;
			double cx = subTexture.cx;	double centerx =	(x + cx) / width;
			double cy = subTexture.cy;	double centery = 	(y + cy) / height;
			
			return new SlpTexture(left, right, bottom, top, centerx, centery);
		}).filter(slp -> slp != null).toArray(SlpTexture[]::new);
	}
	
	private void cannotBind() {	throw new AssertionError("bind.parentTexture"); }
	public void bottomLeft(Coordinator bind) throws LWJGLException {	cannotBind();	}
	public void bottomRight(Coordinator bind) throws LWJGLException {	cannotBind();	}
	public void topRight(Coordinator bind) throws LWJGLException {		cannotBind();	}
	public void topLeft(Coordinator bind) throws LWJGLException {		cannotBind();	}
	public void center(Coordinator bind) throws LWJGLException {		cannotBind();	}
	
	public SlpTexture get(int index) {	return subTextures[index];	}
	public int count() { return subTextures.length;	}
	
	public void destroy(int id) throws LWJGLException {
		texture.destroy(id);
		this.subTextures = null;
	}
}
