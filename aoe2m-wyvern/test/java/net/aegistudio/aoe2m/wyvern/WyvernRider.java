package net.aegistudio.aoe2m.wyvern;

import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class WyvernRider {
	protected String title() { return "Wyvern";	}
	
	protected int width() { return 800; }
	
	protected int height() { return 480; }

	protected void prepare() throws LWJGLException {	}
	protected void render() throws LWJGLException {	}
	protected void dispose() throws LWJGLException {	}
	
	public @Test void test() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width(), height()));
		Display.create();
		
		prepare();
		
		while(!Display.isCloseRequested()) {
			Display.setTitle(title());
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			render();
			
			Display.update();
			Display.sync(60);
		}
		dispose();
		Display.destroy();
	}
}
