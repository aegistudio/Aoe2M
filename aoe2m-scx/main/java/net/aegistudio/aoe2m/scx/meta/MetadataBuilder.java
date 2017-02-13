package net.aegistudio.aoe2m.scx.meta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.io.FieldInputStream;
import net.aegistudio.aoe2m.io.FieldOutputStream;
import net.aegistudio.aoe2m.scx.ScxConstants;
import net.aegistudio.aoe2m.scx.Text;
import net.aegistudio.aoe2m.scx.msg.Message;

public class MetadataBuilder {
	private MetadataPo metadata;
	private GlobalVictoryPo victory;
	
	public MetadataBuilder(MetadataPo metadata, GlobalVictoryPo victory) {
		this.metadata = metadata;
		this.victory = victory;
	}
	
	public void readUncompressedHeader(FieldInputStream fin, Message message)
			throws CorruptionException, IOException {
		
		String versionCode = fin.readConstLengthString(4);
		metadata.version = EnumVersion.getVersion(versionCode);
		
		long headerLength = fin.readUnsigned32();
		
		CorruptionException.assertInt(2, fin.readSigned32());
		headerLength -= 4;
	
		metadata.lastSavedTimestamp = fin.readUnsigned32();
		headerLength -= 4;
		
		message.instructions.setValue(new Text(fin.readString32()));
		headerLength -= (message.instructions.getValue().length + 4);
		
		CorruptionException.assertLong(0, fin.readUnsigned32());
		headerLength -= 4;
		
		metadata.playerCount = (int) fin.readUnsigned32();
		headerLength -= 4;
		
		if(headerLength != 0) 
			throw new CorruptionException(headerLength, 0);
	}
	
	public void writeUncompressedHeader(FieldOutputStream fout, Message message)
			throws IOException, CorruptionException {
		fout.writeConstString(4, metadata.version.getVersionString());
		
		ByteArrayOutputStream outputTemp = new ByteArrayOutputStream();
		FieldOutputStream fieldTemp = new FieldOutputStream(outputTemp);
		
		fieldTemp.write32(2);
		fieldTemp.write32((int) metadata.lastSavedTimestamp);
		fieldTemp.writeString32(message.instructions.getValue().string());
		fieldTemp.write32(0);
		fieldTemp.write32(metadata.playerCount);
		
		fout.write32(outputTemp.toByteArray().length);
		outputTemp.writeTo(fout);
		
		fieldTemp.close();
	}
	
	public void buildCompressedHeaderPre(Translator translator) throws IOException, CorruptionException {
		translator.unsigned32(metadata.nextUnitId);
		translator.constFloat(metadata.version.getVersionFloat());
	}
	
	public void buildCompressedHeaderTail(Translator translator) throws IOException, CorruptionException {
		translator.constInteger(1);
		translator.constInteger((int)2147483648l);
		translator.constByte(191);
		
		translator.string16(metadata.originalFileName.stringWrapper());
	}
	
	public void buildGlobalVictory(Translator translator) throws IOException, CorruptionException {
		ScxConstants.division(translator);
		translator.bool32(victory.customConquer);
		ScxConstants.unused(translator);

		translator.unsigned32(victory.customMinRelic);
		ScxConstants.unused(translator);
		
		translator.unsigned32(victory.customExplorePercent);
		ScxConstants.unused(translator);
		
		translator.bool32(victory.allCustomCondition);
		translator.enum32(victory.victoryMode.enumWrapper());
		
		translator.unsigned32(victory.requiredScore);
		translator.unsigned32(victory.requiredTime);
	}
}
