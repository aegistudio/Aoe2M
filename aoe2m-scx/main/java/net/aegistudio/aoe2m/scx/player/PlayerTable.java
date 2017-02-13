package net.aegistudio.aoe2m.scx.player;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.Text;

public class PlayerTable {
	public final PlayerData playerData[] = new PlayerData[16]; { 
		for(int i = 0; i < 16; i ++) {
			playerData[i] = new PlayerData(); 
			playerData[i].constPlayerName.setValue(new Text("Player " + (i + 1)));
			playerData[i].diplomacy[i].setValue(EnumDiplomacy.ALLIED);
		}
	}
	
	public final Wrapper<Boolean> allTechs = new Container<Boolean>(false);
	public final Wrapper<Long> playerData3Length = new Container<Long>(9l);
}
