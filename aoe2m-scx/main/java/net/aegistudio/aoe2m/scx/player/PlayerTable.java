package net.aegistudio.aoe2m.scx.player;

import net.aegistudio.uio.*;
import net.aegistudio.uio.wrap.*;
import net.aegistudio.aoe2m.scx.Text;

public class PlayerTable {
	public final PlayerData playerData[] = new PlayerData[16]; { 
		for(int i = 0; i < 16; i ++) {
			playerData[i] = new PlayerData(); 
			playerData[i].constPlayerName.set(new Text("Player " + (i + 1)));
			playerData[i].diplomacy[i].set(EnumDiplomacy.ALLIED);
		}
	}
	
	public final BooleanContainer allTechs = new BooleanContainer(false);
	public final Wrapper<Long> playerData3Length = new Container<Long>(9l);
}
