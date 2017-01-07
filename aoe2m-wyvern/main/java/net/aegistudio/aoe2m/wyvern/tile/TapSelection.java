package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.FrameRenderObject;

public abstract class TapSelection {
	public final FrameRenderObject accessFrameObject;
	public TapSelection(FrameRenderObject frame) {
		accessFrameObject = frame;
	}

	public void selectionTest(Terrain terrain, int x, int y, int w, int h) throws LWJGLException {
		double texU = 1.0 * x / w;
		double texV = 1.0 * y / h;
		
		float[][][] result = accessFrameObject.download(
				(int)(accessFrameObject.width * texU),
				(int)(accessFrameObject.height * texV), 1, 1);
		if(result[0][0][3] != 0)
			select(result[0][0][0] * terrain.width(), 
					result[0][0][1] * terrain.height(), 
					result[0][0][2] * 16);
	}
	
	protected abstract void select(double x, double y, double z);
}
