package net.aegistudio.aoe2m.scx.player;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.scx.ScxConstants;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class PlayerTableBuilder {
	private PlayerTable playerTable;
	public PlayerTableBuilder(PlayerTable playerTable) {
		this.playerTable = playerTable;
	}
	
	public void buildPlayerData1(MetadataPo metadata, Translator translator) throws IOException, CorruptionException {
		for(int i = 0; i < 16; i ++) 
			translator.string(256, playerTable.playerData[i].asciiPlayerName);
		
		if(metadata.version.getVersionFloat() >= 1.18f)
			for(int i = 0; i < 16; i ++)
				translator.unsigned32(playerTable.playerData[i].stringTableIndex);
		
		for(int i = 0; i < 16; i ++) {
			translator.bool32(playerTable.playerData[i].active);
			translator.bool32(playerTable.playerData[i].human);
			translator.enum32(playerTable.playerData[i].civilization.enumWrapper());
			translator.constInteger(4);
		}
	}
	
	public void buildPlayerData2(MetadataPo metadata, Translator translator) throws IOException, CorruptionException {
		for(int i = 0; i < 16; i ++) 
			translator.string16(playerTable.playerData[i].vcName);
		
		for(int i = 0; i < 16; i ++) 
			translator.string16(playerTable.playerData[i].ctyName);
		
		for(int i = 0; i < 16; i ++) 
			translator.string16(playerTable.playerData[i].aiName);
		
		for(int i = 0; i < 16; i ++) {
			ScxConstants.unused(translator);
			ScxConstants.unused(translator);
			translator.string32(playerTable.playerData[i].perFile);
		}
		
		for(int i = 0; i < 16; i ++)
			translator.enum8(playerTable.playerData[i].aiType.enumWrapper());
		
		ScxConstants.division(translator);
		
		for(int i = 0; i < 16; i ++) {
			translator.unsigned32(playerTable.playerData[i].initGold);
			translator.unsigned32(playerTable.playerData[i].initWood);
			translator.unsigned32(playerTable.playerData[i].initFood);;
			translator.unsigned32(playerTable.playerData[i].initStone);
			translator.unsigned32(playerTable.playerData[i].initOreX);
			ScxConstants.unused(translator);
		}
	}
	
	public void buildDiplomacy(MetadataPo metadata, Translator translator) throws IOException, CorruptionException {
		for(int i = 0; i < 16; i ++) 
			for(int j = 0; j < 16; j ++) 
				translator.enum32(playerTable.playerData[i].diplomacy[j].enumWrapper());
			
		translator.skip(11520);	// 16 * 720, WTF
		ScxConstants.division(translator);
		
		for(int i = 0; i < 16; i ++)
			translator.bool32(playerTable.playerData[i].alliedVictory);
	}
	
	public void buildDisable(MetadataPo metadata, Translator translator) throws IOException, CorruptionException {
		// Disabled techs
		for(int i = 0; i < 16; i ++) playerTable.playerData[i]
				.diasabledTechs.buildCount(translator);
		for(int i = 0; i < 16; i ++) playerTable.playerData[i]
				.diasabledTechs.buildDisabledId(translator);
		if(metadata.version.getVersionFloat() >= 1.30f)
			for(int i = 0; i < 16; i ++) playerTable.playerData[i]
					.diasabledTechs.buildExtraDisabledId(translator);

		// Disabled units
		for(int i = 0; i < 16; i ++) playerTable.playerData[i]
				.diasabledUnits.buildCount(translator);
		for(int i = 0; i < 16; i ++) playerTable.playerData[i]
				.diasabledUnits.buildDisabledId(translator);
		if(metadata.version.getVersionFloat() >= 1.30f)
			for(int i = 0; i < 16; i ++) playerTable.playerData[i]
					.diasabledUnits.buildExtraDisabledId(translator);
		
		// Disabled buildings
		for(int i = 0; i < 16; i ++) playerTable.playerData[i]
				.diasabledBuildings.buildCount(translator);
		for(int i = 0; i < 16; i ++) playerTable.playerData[i]
				.diasabledBuildings.buildDisabledId(translator);
		if(metadata.version.getVersionFloat() >= 1.30f)
			for(int i = 0; i < 16; i ++) playerTable.playerData[i]
					.diasabledBuildings.buildExtraDisabledId(translator);
		
		ScxConstants.unused(translator);
		ScxConstants.unused(translator);
		translator.bool32(playerTable.allTechs);
		for(int i = 0; i < 16; i ++)
			translator.signed32(playerTable.playerData[i].startingAge);
	}
	

	public void buildPlayerData4(MetadataPo metadata, Translator translator) throws IOException, CorruptionException {
		for(int i = 0; i < 8; i ++) {
			translator.constFloat(playerTable.playerData[i].initFood.getValue());
			translator.constFloat(playerTable.playerData[i].initWood.getValue());
			translator.constFloat(playerTable.playerData[i].initGold.getValue());
			translator.constFloat(playerTable.playerData[i].initStone.getValue());
			translator.constFloat(playerTable.playerData[i].initOreX.getValue());
			translator.constFloat(0.0f);
			translator.float32(playerTable.playerData[i].populationLimit);
		}
	}
	
	public void buildPlayerData3(MetadataPo metadata, Translator translator) throws IOException, CorruptionException {
		translator.unsigned32(playerTable.playerData3Length);
		for(int i = 0; i < playerTable.playerData3Length.getValue() - 1; i ++) {
			PlayerData playerData = this.playerTable.playerData[i];
			translator.string16(playerData.constPlayerName.stringWrapper());
			
			translator.float32(playerData.cameraX);
			translator.float32(playerData.cameraY);
			translator.signed16(playerData.unknownedX);
			translator.signed16(playerData.unknownedY);
			
			translator.constByte(playerData.alliedVictory.getValue()? 1: 0);
			translator.constShort((int)(long)playerTable.playerData3Length.getValue());

			translator.constByte(EnumDiplomacy.ENEMY.ordinal());
			for(int d = 0; d < playerTable.playerData3Length.getValue() - 1; d ++) {
				translator.enum8(this.playerTable.playerData[i].diplomacy[d].enumWrapper());
			}
			
			translator.constInteger(0);
			for(int d = 0; d < playerTable.playerData3Length.getValue() - 1; d ++) {
				if(d == i) translator.constInteger(1);
				else translator.constInteger(this.playerTable.playerData[i].diplomacy[d]
						.getValue().auxiliaryOrder);
			}
			
			// The .scx format doesn't handle the entry correctly.
			translator.constInteger(i); // WTF?
			
			translator.float32(playerData.unknownedArrayIncluded);
			translator.enum32(playerData.playerColor.enumWrapper());
			translator.signed16(playerData.grandTheftEmpirePlayers);
			translator.constShort(0);
			
			if(playerData.unknownedArrayIncluded.getValue() == 2.0f) translator.skip(8);
			translator.skip(44 * playerData.grandTheftEmpirePlayers.getValue());

			translator.skip(1);
			
			translator.constInteger((int)(playerData.unknownedArrayIncluded
					.getValue() == 2.0f? 0x0ffffffffl: 0x00000000l));
		}
		translator.constInteger((int)0x09999999al);
		translator.constInteger((int)0x03ff99999l);
	}
}

