package net.aegistudio.aoe2m.wyvern.unit;

import org.lwjgl.LWJGLException;

import static org.lwjgl.opengl.GL11.*;

import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.ProfileShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class ProfileRenderer extends BasicRenderer {
	public final TextureManager textureManager;
	public final TextureBufferObject priority;
	public final ProfileShaderProgram shaderProgram;
	public final Arbitrator arbitrator;
	public ProfileRenderer(GraphicsManager manager, TileOutline outline,
			TextureManager textureManager, TextureBufferObject priority, 
			ProfileShaderProgram shaderProgram, Arbitrator arbitrator) {
		super(manager, outline);
		this.textureManager = textureManager;
		this.priority = priority;
		this.shaderProgram = shaderProgram;
		this.arbitrator = arbitrator;
	}
	
	@Override
	public void prepare() throws LWJGLException {
		priority.begin();
		shaderProgram.use();
		
		glEnable(GL_DEPTH_TEST);	
		glDepthFunc(GL_LEQUAL);
		
		glClearColor(0, 0, 0, 0);
		glClearDepth(1);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void subDraw(Terrain terrain, GraphicsInstruction instruction, 
			GraphicsSprite sprite, int slpIndex, SlpSubImage subImage) throws LWJGLException {
		
		arbitrator.priority(shaderProgram.priority, terrain, instruction);
		
		textureManager.bind(sprite.normalTexture, shaderProgram.normal);
		textureManager.bind(sprite.playerTexture, shaderProgram.player);
		
		SlpTexture slpTexture = sprite.normalTexture.get(slpIndex);
		
		actualDraw(terrain, instruction, sprite, subImage,
				() -> slpTexture.topLeft(shaderProgram::texCoord),
				() -> slpTexture.topRight(shaderProgram::texCoord),
				() -> slpTexture.bottomRight(shaderProgram::texCoord), 
				() -> slpTexture.bottomLeft(shaderProgram::texCoord));
	}

	@Override
	public void cleanup() throws LWJGLException {
		shaderProgram.unuse();
		priority.end();
	}
	
}
