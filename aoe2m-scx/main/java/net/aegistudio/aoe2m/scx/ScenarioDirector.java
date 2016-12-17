package net.aegistudio.aoe2m.scx;

import net.aegistudio.aoe2m.scx.map.MapBuilder;
import net.aegistudio.aoe2m.scx.meta.MetadataBuilder;
import net.aegistudio.aoe2m.scx.msg.MessageBuilder;
import net.aegistudio.aoe2m.scx.player.PlayerTableBuilder;
import net.aegistudio.aoe2m.scx.trigger.TriggerBuilder;

public class ScenarioDirector {
	public void build(Scenario scenario, FieldTranslator fieldTranslator) throws Exception {
		MetadataBuilder metadata = new MetadataBuilder(scenario.metadata, scenario.globalVictory);
		PlayerTableBuilder playerTable = new PlayerTableBuilder(scenario.player);
		MessageBuilder message = new MessageBuilder(scenario.message, scenario.cinematic);
		MapBuilder map = new MapBuilder(scenario.map);
		TriggerBuilder trigger = new TriggerBuilder(scenario.trigger);
		
		metadata.buildCompressedHeaderPre(fieldTranslator);
		playerTable.buildPlayerData1(scenario.metadata, fieldTranslator);
		metadata.buildCompressedHeaderTail(fieldTranslator);
		
		message.buildMessage(scenario.metadata, fieldTranslator);
		message.buildCinematic(scenario.metadata, fieldTranslator);
		
		playerTable.buildPlayerData2(scenario.metadata, fieldTranslator);
		metadata.buildGlobalVictory(fieldTranslator);
		
		playerTable.buildDiplomacy(scenario.metadata, fieldTranslator);
		playerTable.buildDisable(scenario.metadata, fieldTranslator);
		
		map.buildTerrian(scenario.metadata, fieldTranslator);
		playerTable.buildPlayerData4(scenario.metadata, fieldTranslator);
		map.buildUnits(scenario.metadata, fieldTranslator);
		
		playerTable.buildPlayerData3(scenario.metadata, fieldTranslator);
		
		trigger.buildTriggerSection(scenario.metadata, fieldTranslator);
		
		scenario.include.build(fieldTranslator);
		
		fieldTranslator.end();
	}
}
