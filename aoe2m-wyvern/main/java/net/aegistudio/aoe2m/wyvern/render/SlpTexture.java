package net.aegistudio.aoe2m.wyvern.render;

import org.lwjgl.LWJGLException;

public class SlpTexture implements Texture {
	protected final double left, right, bottom, top, centerx, centery;
	
	public SlpTexture(double left, double right, 
			double bottom, double top, double centerx, double centery) {
		this.left = left;
		this.right = right;
		this.bottom = bottom;
		this.top = top;
		this.centerx = centerx;
		this.centery = centery;
	}
	
	public void bottomLeft(TextureCoord bind) throws LWJGLException {	bind.coord(left, bottom);		}
	public void bottomRight(TextureCoord bind) throws LWJGLException {	bind.coord(right, bottom);		}
	public void topRight(TextureCoord bind) throws LWJGLException {		bind.coord(right, top);			}
	public void topLeft(TextureCoord bind) throws LWJGLException {		bind.coord(left, top);			}
	public void center(TextureCoord bind) throws LWJGLException {		bind.coord(centerx, centery);	}
	
	public void make(int id) throws LWJGLException {	}
}
