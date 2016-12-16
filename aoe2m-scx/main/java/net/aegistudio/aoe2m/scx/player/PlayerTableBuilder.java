package net.aegistudio.aoe2m.scx.player;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.String16;
import net.aegistudio.aoe2m.scx.Wrapper;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class PlayerTableBuilder {
	private PlayerData playerData[] = new PlayerData[16]; { 
		for(int i = 0; i < 16; i ++) {
			playerData[i] = new PlayerData(); 
			playerData[i].constPlayerName.setValue(new String16("Player " + i));
			playerData[i].diplomacy[i].setValue(EnumDiplomacy.ALLIED);
		}
	}
	
	public void buildPlayerData1(MetadataPo metadata, FieldTranslator translator) throws Exception {
		for(int i = 0; i < 16; i ++) 
			translator.constString(256, playerData[i].asciiPlayerName);
		
		if(metadata.version.getVersionFloat() >= 1.18f)
			for(int i = 0; i < 16; i ++)
				translator.unsigned32(playerData[i].stringTableIndex);
		
		for(int i = 0; i < 16; i ++) {
			translator.bool32(playerData[i].active);
			translator.bool32(playerData[i].human);
			translator.enum32(playerData[i].civilization);
			translator.constUnsigned32(4);
		}
	}
	
	public void buildPlayerData2(MetadataPo metadata, FieldTranslator translator) throws Exception {
		for(int i = 0; i < 16; i ++) {
			translator.string16(playerData[i].unknownString1);
			translator.string16(playerData[i].unknownString2);
		}
		
		for(int i = 0; i < 16; i ++) 
			translator.string16(playerData[i].aiName);
		
		for(int i = 0; i < 16; i ++) {
			translator.unused();	translator.unused();
			translator.string32(playerData[i].perFile);
		}
		
		for(int i = 0; i < 16; i ++)
			translator.enum8(playerData[i].aiType);
		
		translator.division();
		
		for(int i = 0; i < 16; i ++) {
			translator.unsigned32(playerData[i].initGold);
			translator.unsigned32(playerData[i].initWood);
			translator.unsigned32(playerData[i].initFood);;
			translator.unsigned32(playerData[i].initStone);
			translator.unsigned32(playerData[i].initOreX);
			translator.unused();
		}
	}
	
	public void buildDiplomacy(MetadataPo metadata, FieldTranslator translator) throws Exception {
		for(int i = 0; i < 16; i ++) 
			for(int j = 0; j < 16; j ++) 
				translator.enum32(playerData[i].diplomacy[j]);
			
		translator.skip(11520);	// 16 * 720, WTF
		translator.division();
		
		for(int i = 0; i < 16; i ++)
			translator.bool32(playerData[i].alliedVictory);
	}
	
	public void buildDisable(MetadataPo metadata, FieldTranslator translator) throws Exception {
		// Disabled techs
		for(int i = 0; i < 16; i ++) playerData[i].diasabledTechs.buildCount(translator);
		for(int i = 0; i < 16; i ++) playerData[i].diasabledTechs.buildDisabledId(translator);
		if(metadata.version.getVersionFloat() >= 1.30f)
			for(int i = 0; i < 16; i ++) playerData[i].diasabledTechs.buildExtraDisabledId(translator);

		// Disabled units
		for(int i = 0; i < 16; i ++) playerData[i].diasabledUnits.buildCount(translator);
		for(int i = 0; i < 16; i ++) playerData[i].diasabledUnits.buildDisabledId(translator);
		if(metadata.version.getVersionFloat() >= 1.30f)
			for(int i = 0; i < 16; i ++) playerData[i].diasabledUnits.buildExtraDisabledId(translator);
		
		// Disabled buildings
		for(int i = 0; i < 16; i ++) playerData[i].diasabledBuildings.buildCount(translator);
		for(int i = 0; i < 16; i ++) playerData[i].diasabledBuildings.buildDisabledId(translator);
		if(metadata.version.getVersionFloat() >= 1.30f)
			for(int i = 0; i < 16; i ++) playerData[i].diasabledBuildings.buildExtraDisabledId(translator);
		
		translator.unused();
		translator.unused();
		translator.bool32(allTechs);
		for(int i = 0; i < 16; i ++)
			translator.signed32(playerData[i].startingAge);
	}
	

	public void buildPlayerData4(MetadataPo metadata, FieldTranslator translator) throws Exception {
		for(int i = 0; i < 8; i ++) {
			translator.constFloat(playerData[i].initFood.getValue());
			translator.constFloat(playerData[i].initWood.getValue());
			translator.constFloat(playerData[i].initGold.getValue());
			translator.constFloat(playerData[i].initStone.getValue());
			translator.constFloat(playerData[i].initOreX.getValue());
			translator.constFloat(0.0f);
			translator.float32(playerData[i].populationLimit);
		}
	}
	
	Wrapper<Long> playerData3Length = new Wrapper<Long>(9l);
	public Wrapper<Long> getPlayerData3Length() {
		return this.playerData3Length;
	}
	public void buildPlayerData3(MetadataPo metadata, FieldTranslator translator) throws Exception {
		translator.unsigned32(playerData3Length);
		for(int i = 0; i < playerData3Length.getValue() - 1; i ++) {
			PlayerData playerData = this.playerData[i];
			translator.string16(playerData.constPlayerName);
			
			translator.float32(playerData.cameraX);
			translator.float32(playerData.cameraY);
			translator.signed16(playerData.unknownedX);
			translator.signed16(playerData.unknownedY);
			
			translator.constByte(playerData.alliedVictory.getValue()? 1: 0);
			translator.constUnsigned16((int)(long)playerData3Length.getValue());

			translator.constByte(EnumDiplomacy.ENEMY.ordinal());
			for(int d = 0; d < playerData3Length.getValue() - 1; d ++) {
				translator.enum8(this.playerData[i].diplomacy[d]);
			}
			
			translator.constUnsigned32(0);
			for(int d = 0; d < playerData3Length.getValue() - 1; d ++) {
				if(d == i) translator.constUnsigned32(1);
				else translator.constUnsigned32(this.playerData[i].diplomacy[d]
						.getValue().auxiliaryOrder);
			}
			
			// The .scx format doesn't handle the entry correctly.
			translator.constUnsigned32(i); // WTF?
			
			translator.float32(playerData.unknownedArrayIncluded);
			translator.enum32(playerData.playerColor);
			translator.signed16(playerData.grandTheftEmpirePlayers);
			translator.constUnsigned16(0);
			
			if(playerData.unknownedArrayIncluded.getValue() == 2.0f) translator.skip(8);
			translator.skip(44 * playerData.grandTheftEmpirePlayers.getValue());

			translator.skip(1);
			
			translator.constUnsigned32(playerData.unknownedArrayIncluded
					.getValue() == 2.0f? 0x0ffffffffl: 0x00000000l);
		}
		translator.constUnsigned32(0x09999999al);
		translator.constUnsigned32(0x03ff99999l);
	}
	
	public Wrapper<Boolean> allTechs = new Wrapper<Boolean>(false);
	
	public PlayerData[] getPlayerTable() {
		return this.playerData;
	}
}
