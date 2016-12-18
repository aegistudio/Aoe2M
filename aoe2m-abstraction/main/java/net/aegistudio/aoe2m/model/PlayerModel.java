package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.player.PlayerData;
import net.aegistudio.aoe2m.scx.player.PlayerTable;

public interface PlayerModel extends ModelObject {
	public void marshal(Scenario sceario, PlayerTable table, PlayerData player) 
			throws Aoe2mException;
}
