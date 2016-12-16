package net.aegistudio.aoe2m.scx;

import java.util.Arrays;

import net.aegistudio.aoe2m.scx.map.MapBuilder;
import net.aegistudio.aoe2m.scx.map.MapPo;
import net.aegistudio.aoe2m.scx.meta.GlobalVictoryPo;
import net.aegistudio.aoe2m.scx.meta.MetadataBuilder;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;
import net.aegistudio.aoe2m.scx.msg.Cinematic;
import net.aegistudio.aoe2m.scx.msg.Message;
import net.aegistudio.aoe2m.scx.msg.MessageBuilder;
import net.aegistudio.aoe2m.scx.player.PlayerData;
import net.aegistudio.aoe2m.scx.player.PlayerTableBuilder;
import net.aegistudio.aoe2m.scx.trigger.TriggerBuilder;
import net.aegistudio.aoe2m.scx.trigger.TriggerListPo;

public class Scenario implements ScenarioDirector.Directable {
	@Override
	public MetadataBuilder getMetadataBuilder() {
		return metadata;
	}

	@Override
	public PlayerTableBuilder getPlayerTableBuilder() {
		return playerTable;
	}

	@Override
	public MessageBuilder getMessageBuilder() {
		return message;
	}
	
	MetadataBuilder metadata = new MetadataBuilder();
	public MetadataPo getMetadata() {
		return this.metadata.getMetadata();
	}
	
	public GlobalVictoryPo getGlobalVictory() {
		return this.metadata.getGlobalVictory();
	}
	
	PlayerTableBuilder playerTable = new PlayerTableBuilder();
	public PlayerData[] getPlayerTable() {
		return this.playerTable.getPlayerTable();
	}
	
	MessageBuilder message = new MessageBuilder();
	public Message getMessage() {
		return this.message.getMessage();
	}
	
	public Cinematic getCinematic() {
		return this.message.getCinematic();
	}
	
	MapBuilder map = new MapBuilder();
	public MapBuilder getMapBuilder() {
		return this.map;
	}
	
	public MapPo getMap() {
		return this.map.getMap();
	}

	TriggerBuilder trigger = new TriggerBuilder();
	@Override
	public TriggerBuilder getTriggerBuilder() {
		return trigger;
	}
	
	public TriggerListPo getTriggerList() {
		return trigger.getTriggerList();
	}
	
	public String toString() {
		StringFormater toString = new StringFormater(this);
		toString.add("Metadata", getMetadata());
		toString.line();
		
		toString.add("GlobalVictory", getGlobalVictory());
		toString.line();
		
		toString.add("Message", getMessage());
		toString.line();
		
		toString.add("Cinematic", getCinematic());
		toString.line();
		
		toString.add("Map", getMap());
		toString.line();
		
		toString.add("Player", Arrays.asList(getPlayerTable()).subList(0, 
				this.getMetadata().playerCount));
		
		toString.add("AllTechs", getPlayerTableBuilder().allTechs.getValue());
		
		return toString.toString();
	}
}
