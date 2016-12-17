package net.aegistudio.aoe2m.scx.meta;

import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.input.FieldInputStream;

public class MetadataBuilder {
	private MetadataPo metadata;
	private GlobalVictoryPo victory;
	private long headerLength;
	
	public MetadataBuilder(MetadataPo metadata, GlobalVictoryPo victory) {
		this.metadata = metadata;
		this.victory = victory;
	}
	
	public void readUncompressedHeader(FieldInputStream fin) throws Exception {
		String versionCode = fin.readConstLengthString(4);
		metadata.version = EnumVersion.getVersion(versionCode);
		
		this.headerLength = fin.readUnsigned32();
		
		CorruptionException.assertInt(2, fin.readSigned32());
		this.headerLength -= 4;
		
		metadata.lastSavedTimestamp = fin.readUnsigned32();
		this.headerLength -= 4;
		
		metadata.scenarioInstruction = fin.readString32();
		this.headerLength -= (metadata.scenarioInstruction.length + 4);
		
		CorruptionException.assertLong(0, fin.readUnsigned32());
		this.headerLength -= 4;
		
		metadata.playerCount = (int) fin.readUnsigned32();
		this.headerLength -= 4;
		
		if(this.headerLength != 0) 
			throw new Exception("End of header reached, but length field indicates " + this.headerLength + " remaining!");
	}
	
	public void buildCompressedHeaderPre(FieldTranslator translator) throws Exception {
		translator.unsigned32(metadata.nextUnitId);
		translator.constFloat(metadata.version.getVersionFloat());
	}
	
	public void buildCompressedHeaderTail(FieldTranslator translator) throws Exception {
		translator.constUnsigned32(1);
		translator.constUnsigned32(2147483648l);
		translator.constByte(191);
		
		translator.string16(metadata.originalFileName);
	}
	
	public void buildGlobalVictory(FieldTranslator translator) throws Exception {
		translator.division();
		translator.bool32(victory.customConquer);
		translator.unused();

		translator.unsigned32(victory.customMinRelic);
		translator.unused();
		
		translator.unsigned32(victory.customExplorePercent);
		translator.unused();
		
		translator.bool32(victory.allCustomCondition);
		translator.enum32(victory.victoryMode);
		
		translator.unsigned32(victory.requiredScore);
		translator.unsigned32(victory.requiredTime);
	}
}
