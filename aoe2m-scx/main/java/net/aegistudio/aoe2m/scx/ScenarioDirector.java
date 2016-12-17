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
		playerTable.buildPlayerData1(metadata.getMetadata(), fieldTranslator);
		metadata.buildCompressedHeaderTail(fieldTranslator);
		
		message.buildMessage(metadata.getMetadata(), fieldTranslator);
		message.buildCinematic(metadata.getMetadata(), fieldTranslator);
		
		playerTable.buildPlayerData2(metadata.getMetadata(), fieldTranslator);
		metadata.buildGlobalVictory(fieldTranslator);
		
		playerTable.buildDiplomacy(metadata.getMetadata(), fieldTranslator);
		playerTable.buildDisable(metadata.getMetadata(), fieldTranslator);
		
		map.buildTerrian(metadata.getMetadata(), fieldTranslator);
		playerTable.buildPlayerData4(metadata.getMetadata(), fieldTranslator);
		map.buildUnits(metadata.getMetadata(), fieldTranslator);
		
		playerTable.buildPlayerData3(metadata.getMetadata(), fieldTranslator);
		
		trigger.buildTriggerSection(metadata.getMetadata(), fieldTranslator);
		
		// AI files, will be emitted currently.
		fieldTranslator.constUnsigned32(0l);
		fieldTranslator.constUnsigned32(0l);
		
		fieldTranslator.end();
	}
}
