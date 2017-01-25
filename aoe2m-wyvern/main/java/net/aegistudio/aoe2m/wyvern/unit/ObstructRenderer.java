package net.aegistudio.aoe2m.wyvern.unit;

import java.util.function.Supplier;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.PlayerPalette;
import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.ObstructShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class ObstructRenderer extends BasicRenderer {
	protected final Supplier<PlayerPalette> paletteSupplier;
	protected final ObstructShaderProgram obstructProgram;
	protected final TextureBufferObject profileTexture;
	protected final Arbitrator arbitrator;
	protected final TextureManager textureManager;
	protected final PlayerPaletteHint paletteHint;
	
	public ObstructRenderer(ObstructShaderProgram obstructProgram, Supplier<PlayerPalette> paletteSupplier,
			PlayerPaletteHint paletteHint, TextureBufferObject profileTexture, Arbitrator arbitrator,
			GraphicsManager manager, TileOutline outline, TextureManager textureManager) {
		super(manager, outline);
		this.paletteSupplier = paletteSupplier;
		this.textureManager = textureManager;
		this.profileTexture = profileTexture;
		this.arbitrator = arbitrator;
		this.obstructProgram = obstructProgram;
		this.paletteHint = paletteHint;
	}
	
	@Override
	public void prepare() throws LWJGLException {
		obstructProgram.use();
	}
	
	public void subDraw(Terrain terrain, GraphicsInstruction instruction, 
			GraphicsSprite sprite, int slpTextureIndex, SlpSubImage subImage) throws LWJGLException {
		
		// Parse player color.
		obstructProgram.outline(paletteSupplier.get().subLength / 3);
		paletteHint.playerIndex(obstructProgram.playerPalette, instruction);
		
		// Use the texture.
		SlpParentTexture texture = sprite.obstructTexture;
		textureManager.bind(texture, obstructProgram.obstruct);
		SlpTexture slpTexture = texture.get(slpTextureIndex);
		
		// Use the profile.
		textureManager.bind(profileTexture, 
				obstructProgram.priorityMap.map);
		
		// Set priority.
		arbitrator.priority(obstructProgram.priority, terrain, instruction);
		
		// Render actually.
		actualDraw(terrain, instruction, sprite, subImage,
				() -> slpTexture.topLeft(obstructProgram.obstruct), 
				() -> slpTexture.topRight(obstructProgram.obstruct), 
				() -> slpTexture.bottomRight(obstructProgram.obstruct), 
				() -> slpTexture.bottomLeft(obstructProgram.obstruct));
	}

	@Override
	public void cleanup() throws LWJGLException {
		obstructProgram.unuse();
	}
}
