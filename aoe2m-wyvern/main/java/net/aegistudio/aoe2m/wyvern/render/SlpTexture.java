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
		this.centerx = 0.5 * (left + right);
		this.centery = 0.5 * (top + bottom);
	}
	
	public void bottomLeft(Coordinator bind) throws LWJGLException {	bind.coord(left, bottom);		}
	public void bottomRight(Coordinator bind) throws LWJGLException {	bind.coord(right, bottom);		}
	public void topRight(Coordinator bind) throws LWJGLException {		bind.coord(right, top);			}
	public void topLeft(Coordinator bind) throws LWJGLException {		bind.coord(left, top);			}
	public void center(Coordinator bind) throws LWJGLException {		bind.coord(centerx, centery);	}
	
	public void left(Coordinator bind) throws LWJGLException {			bind.coord(left, centery);		}
	public void bottom(Coordinator bind) throws LWJGLException {		bind.coord(centerx, bottom);	}
	public void right(Coordinator bind) throws LWJGLException {			bind.coord(right, centery);		}
	public void top(Coordinator bind) throws LWJGLException {			bind.coord(centerx, top);		}
	
	public void make(int id) throws LWJGLException {
		throw new LWJGLException("Invalid binding of slp subtexture!");
	}
}
