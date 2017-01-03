package net.aegistudio.aoe2m.assetdba.blob;

/**
 * Describes a subtexture in a SLP texture.
 * 
 * A SLP texture is a composition of subtextures
 * whose boundary and center are described via
 * a subtexture structure.
 * 
 * @author aegistudio
 */

public class SlpSubImage {
	public int x, y, w, h, cx, cy;
	
	public SlpSubImage() {}
	
	public SlpSubImage(int x, int y, int w, int h, int cx, int cy) {
		this.x = x;		this.y = y;
		this.w = w;		this.h = h;
		this.cx = cx;	this.cy = cy;
	}
}