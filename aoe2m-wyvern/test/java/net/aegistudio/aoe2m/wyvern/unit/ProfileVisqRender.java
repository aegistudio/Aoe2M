package net.aegistudio.aoe2m.wyvern.unit;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ARBFramebufferObject;
import static org.lwjgl.opengl.GL11.*;

import net.aegistudio.aoe2m.wyvern.asset.ProfileShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;
import net.aegistudio.aoe2m.wyvern.terrain.BlendingRender;

public class ProfileVisqRender extends BlendingRender {
	protected final GraphicsManager graphicsManager;
	protected final PlacementConsole placement;
	protected final ProfileShaderProgram profileProgram;
	protected final ProfileRenderer profileRenderer;
	protected final TextureBufferObject profileMap;
	protected final ProfileVisqProgram visqProgram;
	
	public ProfileVisqRender() throws IOException, LWJGLException {
		graphicsManager = new GraphicsManager(connection);
		placement = new PlacementConsole(connection.graphics(), graphicsManager);
		profileProgram = new ProfileShaderProgram();
		visqProgram = new ProfileVisqProgram();
		
		profileMap = new TextureBufferObject(300, 300, GL_FLOAT, 
				GL_RGBA, GL_RGBA, ARBFramebufferObject.GL_COLOR_ATTACHMENT0);
		profileRenderer = new ProfileRenderer(graphicsManager, biasOutline, 
				textureManager, profileMap, profileProgram);
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		textureManager.allocate(profileMap);
		profileProgram.create();
		visqProgram.create();
		placement.start();
	}
	
	public void render() throws LWJGLException {
		super.render();
		placement.render(profileRenderer, terrain);
		
		
	}
	
	public void dispose() throws LWJGLException {
		super.dispose();
		placement.stop();
		profileProgram.destroy();
		visqProgram.destroy();
	}
}
