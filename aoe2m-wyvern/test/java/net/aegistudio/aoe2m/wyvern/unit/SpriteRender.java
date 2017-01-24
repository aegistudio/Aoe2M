package net.aegistudio.aoe2m.wyvern.unit;

import java.io.IOException;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.asset.ProfileShaderProgram;
import net.aegistudio.aoe2m.wyvern.asset.SpriteShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;
import net.aegistudio.aoe2m.wyvern.terrain.BlendingRender;

public class SpriteRender extends BlendingRender {
	protected final GraphicsManager graphicsManager;
	protected PlacementConsole placement; // Replacable.
	
	protected final ProfileShaderProgram profileProgram;
	protected final ProfileRenderer profileRenderer;
	protected final TextureBufferObject profileMap;
	protected final Arbitrator arbitrator;

	protected final SpriteShaderProgram spriteProgram;
	protected final SpriteRenderer spriteRenderer;
	
	protected final NullRenderer nullRenderer;
	
	public SpriteRender() throws IOException, LWJGLException {
		super();
		graphicsManager = new GraphicsManager(connection);
		placement = new PlacementConsole(connection.graphics(), graphicsManager);
		profileProgram = new ProfileShaderProgram();
		arbitrator = new CoordinateArbitrator();
		
		profileMap = new ProfileTexture(300, 300);
		profileRenderer = new ProfileRenderer(graphicsManager, biasOutline, 
				textureManager, profileMap, profileProgram, arbitrator);
		
		spriteProgram = new SpriteShaderProgram();
		spriteRenderer = new SpriteRenderer(spriteProgram, profileMap, 
				arbitrator, graphicsManager, outline, textureManager);
		
		nullRenderer = new NullRenderer(graphicsManager, biasOutline, textureManager);
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		textureManager.allocate(profileMap);
		profileMap.create();
		
		profileProgram.create();
		spriteProgram.create();
		placement.start();
	}
	
	public void render() throws LWJGLException {
		super.render();
		placement.render(profileRenderer, terrain);
		placement.render(nullRenderer, terrain);
		placement.render(spriteRenderer, terrain);
	}
	
	public void dispose() throws LWJGLException {
		super.dispose();
		profileMap.destroy();
		profileProgram.destroy();
		spriteProgram.destroy();
	}
}
