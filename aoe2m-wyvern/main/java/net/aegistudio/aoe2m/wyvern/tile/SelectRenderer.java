package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.SelectShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.FrameRenderObject;

public class SelectRenderer implements TileRenderer {
	protected final TileOutline outline;
	protected final SelectShaderProgram program;
	protected final FrameRenderObject offlineFrame;
	
	public SelectRenderer(TileOutline outline, FrameRenderObject offlineFrame, SelectShaderProgram program) {
		this.outline = outline;
		this.program = program;
		this.offlineFrame = offlineFrame;
	}

	@Override
	public void prepare() throws LWJGLException {
		offlineFrame.begin();
		program.use();
		GL11.glClearColor(0, 0, 0, 0);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void render(Terrain terrain, int x, int y) throws LWJGLException {
		program.size(terrain.width(), terrain.height(), 16);
		GL11.glBegin(GL11.GL_QUADS);
			program.coord(x + 0, y + 0, outline.elevation(terrain, x + 0, y + 0));
			outline.left(GL11::glVertex2d, terrain, x, y);

			program.coord(x + 1, y + 0, outline.elevation(terrain, x + 1, y + 0));
			outline.bottom(GL11::glVertex2d, terrain, x, y);
			
			program.coord(x + 1, y + 1, outline.elevation(terrain, x + 1, y + 1));
			outline.right(GL11::glVertex2d, terrain, x, y);
			
			program.coord(x + 0, y + 1, outline.elevation(terrain, x + 0, y + 1));
			outline.top(GL11::glVertex2d, terrain, x, y);
		GL11.glEnd();
	}

	@Override
	public void cleanup() throws LWJGLException {
		program.unuse();
		offlineFrame.end();
	}
}
