package net.aegistudio.aoe2m.wyvern.unit;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.PlayerColorShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class PlayerColorRenderer extends BasicRenderer implements GraphicsRenderer {
	protected final PlayerColorShaderProgram playerColorProgram;
	protected final TextureBufferObject profileTexture;
	protected final Arbitrator arbitrator;
	protected final TextureManager textureManager;
	
	public PlayerColorRenderer(PlayerColorShaderProgram playerColorProgram, 
			TextureBufferObject profileTexture, Arbitrator arbitrator,
			GraphicsManager manager, TileOutline outline, TextureManager textureManager) {
		super(manager, outline);
		this.textureManager = textureManager;
		this.profileTexture = profileTexture;
		this.arbitrator = arbitrator;
		this.playerColorProgram = playerColorProgram;
	}
	
	@Override
	public void prepare() throws LWJGLException {
		playerColorProgram.use();
	}
	
	public void subDraw(Terrain terrain, GraphicsInstruction instruction, 
			GraphicsSprite sprite, int slpTextureIndex, SlpSubImage subImage) throws LWJGLException {
		
		// Parse player color.
		playerColorProgram.playerPalette.player(
				(Integer)instruction.hint.getOrDefault("playerColor.index", 7)); // Gaia
		
		// Use the texture.
		SlpParentTexture texture = sprite.playerTexture;
		textureManager.bind(texture, playerColorProgram.player);
		SlpTexture slpTexture = texture.get(slpTextureIndex);
		
		// Use the profile.
		textureManager.bind(profileTexture, 
				playerColorProgram.priorityMap.map);
		
		// Set priority.
		arbitrator.priority(playerColorProgram.priority, terrain, instruction);
		
		// Render actually.
		actualDraw(terrain, instruction, sprite, subImage,
				() -> slpTexture.topLeft(playerColorProgram.player), 
				() -> slpTexture.topRight(playerColorProgram.player), 
				() -> slpTexture.bottomRight(playerColorProgram.player), 
				() -> slpTexture.bottomLeft(playerColorProgram.player));
	}

	@Override
	public void cleanup() throws LWJGLException {
		playerColorProgram.unuse();
	}
}
