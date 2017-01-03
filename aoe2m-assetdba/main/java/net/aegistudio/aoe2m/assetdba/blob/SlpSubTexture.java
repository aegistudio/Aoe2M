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

public class SlpSubTexture {
	public int x, y, w, h, cx, cy;
	
	public SlpSubTexture() {}
	
	public SlpSubTexture(int x, int y, int w, int h, int cx, int cy) {
		this.x = x;		this.y = y;
		this.w = w;		this.h = h;
		this.x = cx;	this.y = cy;
	}
}