package net.aegistudio.aoe2m.scx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.aegistudio.aoe2m.scx.map.MapPo;
import net.aegistudio.aoe2m.scx.meta.GlobalVictoryPo;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;
import net.aegistudio.aoe2m.scx.msg.Cinematic;
import net.aegistudio.aoe2m.scx.msg.Message;
import net.aegistudio.aoe2m.scx.player.PlayerTable;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;

public class Scenario {
	public final MetadataPo metadata = new MetadataPo();
	public final GlobalVictoryPo globalVictory = new GlobalVictoryPo();
	
	public final PlayerTable player = new PlayerTable();
	
	public final Message message = new Message();
	public final Cinematic cinematic = new Cinematic();
	
	public final MapPo map = new MapPo();

	public final List<TriggerPo> trigger = new ArrayList<>();
	
	public final IncludeSection include = new IncludeSection();
	
	public String toString() {
		StringFormater toString = new StringFormater(this);
		toString.add("Metadata", metadata);
		toString.line();
		
		toString.add("GlobalVictory", globalVictory);
		toString.line();
		
		toString.add("Message", message);
		toString.line();
		
		toString.add("Cinematic", cinematic);
		toString.line();
		
		toString.add("Map", map);
		toString.line();
		
		toString.add("Player", Arrays.asList(player.playerData).subList(0, 
				metadata.playerCount));
		
		toString.add("AllTechs", player.allTechs.getValue());
		
		return toString.toString();
	}
}
