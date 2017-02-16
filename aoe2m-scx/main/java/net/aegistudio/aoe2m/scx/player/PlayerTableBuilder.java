package net.aegistudio.aoe2m.scx.player;

import java.io.IOException;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.aoe2m.scx.ScxConstants;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class PlayerTableBuilder {
	private PlayerTable playerTable;
	public PlayerTableBuilder(PlayerTable playerTable) {
		this.playerTable = playerTable;
	}
	
	public void buildPlayerData1(MetadataPo metadata, Translator translator) throws IOException, CorruptException {
		for(int i = 0; i < 16; i ++) 
			translator.string(256, playerTable.playerData[i].asciiPlayerName);
		
		if(metadata.version.getVersionFloat() >= 1.18f)
			for(int i = 0; i < 16; i ++)
				translator.unsigned32(playerTable.playerData[i].stringTableIndex);
		
		for(int i = 0; i < 16; i ++) {
			translator.signed32(playerTable.playerData[i].active.bool32());
			translator.signed32(playerTable.playerData[i].human.bool32());
			translator.signed32(playerTable.playerData[i].civilization.enum32());
			translator.constInteger(4);
		}
	}
	
	public void buildPlayerData2(MetadataPo metadata, Translator translator) throws IOException, CorruptException {
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
			translator.signed8(playerTable.playerData[i].aiType.enum8());
		
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
	
	public void buildDiplomacy(MetadataPo metadata, Translator translator) throws IOException, CorruptException {
		for(int i = 0; i < 16; i ++) 
			for(int j = 0; j < 16; j ++) 
				translator.signed32(playerTable.playerData[i].diplomacy[j].enum32());
			
		translator.skip(11520);	// 16 * 720, WTF
		ScxConstants.division(translator);
		
		for(int i = 0; i < 16; i ++)
			translator.signed32(playerTable.playerData[i].alliedVictory.bool32());
	}
	
	public void buildDisable(MetadataPo metadata, Translator translator) throws IOException, CorruptException {
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
		translator.signed32(playerTable.allTechs.bool32());
		for(int i = 0; i < 16; i ++)
			translator.signed32(playerTable.playerData[i].startingAge);
	}
	

	public void buildPlayerData4(MetadataPo metadata, Translator translator) throws IOException, CorruptException {
		for(int i = 0; i < 8; i ++) {
			translator.constFloat(playerTable.playerData[i].initFood.get());
			translator.constFloat(playerTable.playerData[i].initWood.get());
			translator.constFloat(playerTable.playerData[i].initGold.get());
			translator.constFloat(playerTable.playerData[i].initStone.get());
			translator.constFloat(playerTable.playerData[i].initOreX.get());
			translator.constFloat(0.0f);
			translator.float32(playerTable.playerData[i].populationLimit);
		}
	}
	
	public void buildPlayerData3(MetadataPo metadata, Translator translator) throws IOException, CorruptException {
		translator.unsigned32(playerTable.playerData3Length);
		for(int i = 0; i < playerTable.playerData3Length.get() - 1; i ++) {
			PlayerData playerData = this.playerTable.playerData[i];
			translator.string16(playerData.constPlayerName.stringWrapper());
			
			translator.float32(playerData.cameraX);
			translator.float32(playerData.cameraY);
			translator.signed16(playerData.unknownedX);
			translator.signed16(playerData.unknownedY);
			
			translator.constByte((byte)(playerData.alliedVictory.get()? 1: 0));
			translator.constShort((short)(long)playerTable.playerData3Length.get());

			translator.constByte((byte)EnumDiplomacy.ENEMY.ordinal());
			for(int d = 0; d < playerTable.playerData3Length.get() - 1; d ++) {
				translator.signed8(this.playerTable.playerData[i].diplomacy[d].enum8());
			}
			
			translator.constInteger(0);
			for(int d = 0; d < playerTable.playerData3Length.get() - 1; d ++) {
				if(d == i) translator.constInteger(1);
				else translator.constInteger(this.playerTable.playerData[i]
						.diplomacy[d].get().auxiliaryOrder);
			}
			
			// The .scx format doesn't handle the entry correctly.
			translator.constInteger(i); // WTF?
			
			translator.float32(playerData.unknownedArrayIncluded);
			translator.signed32(playerData.playerColor.enum32());
			translator.signed16(playerData.grandTheftEmpirePlayers);
			translator.constShort((short)0);
			
			if(playerData.unknownedArrayIncluded.get() == 2.0f) translator.skip(8);
			translator.skip(44 * playerData.grandTheftEmpirePlayers.get());

			translator.skip(1);
			
			translator.constInteger((int)(playerData.unknownedArrayIncluded
					.get() == 2.0f? 0x0ffffffffl: 0x00000000l));
		}
		translator.constInteger((int)0x09999999al);
		translator.constInteger((int)0x03ff99999l);
	}
}

