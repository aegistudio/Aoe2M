package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.StickyShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;

public class StickyRenderer implements TileRenderer {
	protected final TextureManager manager;
	protected final TileOutline outline;
	protected final StickyShaderProgram program;
	protected final StickyTexture texture;
	
	public StickyRenderer(TextureManager manager, TileOutline outline, 
			StickyTexture texture, StickyShaderProgram program) {
		this.outline = outline;
		this.manager = manager;
		this.program = program;
		this.texture = texture;
	}

	@Override
	public void prepare() throws LWJGLException {
		program.use();
		texture.param();
	}
	
	@Override
	public void render(Terrain terrain, int x, int y) throws LWJGLException {
		manager.bind(texture, program.stickyTexture);
		
		GL11.glBegin(GL11.GL_QUADS);
			program.coord(x + 0, y + 0);
			outline.left(GL11::glVertex2d, terrain, x, y);

			program.coord(x + 1, y + 0);
			outline.bottom(GL11::glVertex2d, terrain, x, y);
			
			program.coord(x + 1, y + 1);
			outline.right(GL11::glVertex2d, terrain, x, y);
			
			program.coord(x + 0, y + 1);
			outline.top(GL11::glVertex2d, terrain, x, y);
		GL11.glEnd();
	}

	@Override
	public void cleanup() throws LWJGLException {
		program.unuse();
	}
}
