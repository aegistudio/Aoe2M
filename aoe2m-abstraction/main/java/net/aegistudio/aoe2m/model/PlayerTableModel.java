package net.aegistudio.aoe2m.model;

import java.util.Map;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.Wrapper;
import net.aegistudio.aoe2m.scx.player.PlayerTable;

public interface PlayerTableModel extends ModelObject {
	public Wrapper<PlayerModel> gaia();				/** player.gaia **/
	public Wrapper<PlayerModel> player(int id);		/** player.player[i] / player.playerName **/
	public void remove(int playerId);
	public int playerCount();						/** player.count **/
	
	public Map<String, PlayerModel> predict();
	
	public void marshal(Scenario sceario, PlayerTable player) 
			throws Aoe2mException;
}
