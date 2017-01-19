package net.aegistudio.aoe2m.wyvern.unit;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.ProfileShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class ProfileRenderer extends BasicRenderer {
	public final TextureManager textureManager;
	public final TextureBufferObject priority;
	public final ProfileShaderProgram shaderProgram;
	public ProfileRenderer(GraphicsManager manager, TileOutline outline,
			TextureManager textureManager, TextureBufferObject priority, 
			ProfileShaderProgram shaderProgram) {
		super(manager, outline);
		this.textureManager = textureManager;
		this.priority = priority;
		this.shaderProgram = shaderProgram;
	}
	
	@Override
	public void prepare() throws LWJGLException {
		priority.begin();
		shaderProgram.use();
	}

	@Override
	public void subDraw(Terrain terrain, GraphicsInstruction instruction, GraphicsSprite sprite) throws LWJGLException {
		shaderProgram.priority.boundary(terrain.width(), -terrain.height());
		shaderProgram.priority.priority((float) (instruction.x - instruction.y));
		
		textureManager.bind(sprite.normalTexture, shaderProgram.normal);
		textureManager.bind(sprite.playerTexture, shaderProgram.player);
		
		
	}

	@Override
	public void cleanup() throws LWJGLException {
		shaderProgram.unuse();
		priority.end();
	}
	
}
