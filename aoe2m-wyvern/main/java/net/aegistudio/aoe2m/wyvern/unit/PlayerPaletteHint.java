package net.aegistudio.aoe2m.wyvern.unit;

import net.aegistudio.aoe2m.wyvern.asset.PlayerPaletteObjects;

public class PlayerPaletteHint {
	protected final String playerIndexHint;
	public PlayerPaletteHint() {	this("player.index");	}
	public PlayerPaletteHint(String playerIndexHint) {
		this.playerIndexHint = playerIndexHint;
	}
	
	public void playerIndex(GraphicsInstruction instruction, int value) {
		instruction.hint.put(playerIndexHint, value);
	}
	
	public void playerIndex(PlayerPaletteObjects paletteObjects, GraphicsInstruction instruction) {
		paletteObjects.player((int) instruction.
				hint.getOrDefault(playerIndexHint, 7)); // 7 for GAIA.
	}
}
