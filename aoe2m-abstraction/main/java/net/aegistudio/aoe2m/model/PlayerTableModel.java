package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.player.PlayerTable;

public interface PlayerTableModel extends ListModelObject<PlayerModel>, ModelObject<PlayerTableModel> {
	public void marshal(Scenario sceario, PlayerTable player) 
			throws Aoe2mException;
}
