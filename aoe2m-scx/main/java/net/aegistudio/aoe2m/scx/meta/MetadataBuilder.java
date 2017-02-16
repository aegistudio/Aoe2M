package net.aegistudio.aoe2m.scx.meta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.aegistudio.uio.*;
import net.aegistudio.uio.stream.*;
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
	
	public void readUncompressedHeader(BinaryInputStream fin, Message message)
			throws CorruptException, IOException {
		
		String versionCode = fin.readConstLengthString(4);
		metadata.version = EnumVersion.getVersion(versionCode);
		
		long headerLength = fin.readUnsigned32();
		
		CorruptException.equal(2, fin.readSigned32());
		headerLength -= 4;
	
		metadata.lastSavedTimestamp = fin.readUnsigned32();
		headerLength -= 4;
		
		message.instructions.set(new Text(fin.readString32()));
		headerLength -= (message.instructions.get().length + 4);
		
		CorruptException.equal(0, fin.readUnsigned32());
		headerLength -= 4;
		
		metadata.playerCount = (int) fin.readUnsigned32();
		headerLength -= 4;
		
		if(headerLength != 0) 
			throw new CorruptException(headerLength, 0);
	}
	
	public void writeUncompressedHeader(BinaryOutputStream fout, Message message)
			throws IOException, CorruptException {
		fout.writeConstString(4, metadata.version.getVersionString());
		
		ByteArrayOutputStream outputTemp = new ByteArrayOutputStream();
		BinaryOutputStream fieldTemp = new BinaryOutputStream(outputTemp);
		
		fieldTemp.write32(2);
		fieldTemp.write32((int) metadata.lastSavedTimestamp);
		fieldTemp.writeString32(message.instructions.get().string());
		fieldTemp.write32(0);
		fieldTemp.write32(metadata.playerCount);
		
		fout.write32(outputTemp.toByteArray().length);
		outputTemp.writeTo(fout);
		
		fieldTemp.close();
	}
	
	public void buildCompressedHeaderPre(Translator translator) throws IOException, CorruptException {
		translator.unsigned32(metadata.nextUnitId);
		translator.constFloat(metadata.version.getVersionFloat());
	}
	
	public void buildCompressedHeaderTail(Translator translator) throws IOException, CorruptException {
		translator.constInteger(1);
		translator.constInteger((int)2147483648l);
		translator.constByte((byte)191);
		
		translator.string16(metadata.originalFileName.stringWrapper());
	}
	
	public void buildGlobalVictory(Translator translator) throws IOException, CorruptException {
		ScxConstants.division(translator);
		translator.signed32(victory.customConquer.bool32());
		ScxConstants.unused(translator);

		translator.unsigned32(victory.customMinRelic);
		ScxConstants.unused(translator);
		
		translator.unsigned32(victory.customExplorePercent);
		ScxConstants.unused(translator);
		
		translator.signed32(victory.allCustomCondition.bool32());
		translator.signed32(victory.victoryMode.enum32());
		
		translator.unsigned32(victory.requiredScore);
		translator.unsigned32(victory.requiredTime);
	}
}
