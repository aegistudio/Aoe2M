package net.aegistudio.aoe2m.wyvern.unit;

import java.io.IOException;
import org.lwjgl.LWJGLException;
import net.aegistudio.aoe2m.wyvern.terrain.BlendingRender;

public class SpriteRender extends BlendingRender {
	protected final GraphicsManager graphicsManager;
	protected final SpriteRenderer spriteRenderer;
	protected final PlacementConsole placement;
	
	public SpriteRender() throws IOException, LWJGLException {
		super();
		graphicsManager = new GraphicsManager(connection);
		spriteRenderer = new SpriteRenderer(graphicsManager, outline, textureManager);
		placement = new PlacementConsole(connection.graphics(), graphicsManager);
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		placement.start();
	}
	
	public void render() throws LWJGLException {
		super.render();
		placement.render(spriteRenderer, terrain);
	}
	
	public void dispose() throws LWJGLException {
		super.dispose();
		placement.stop();
	}
}
