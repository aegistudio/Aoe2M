package net.aegistudio.aoe2m.scx.msg;

import java.io.IOException;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.Wrapper;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class MessageBuilder {
	private final Message message;
	private final Cinematic cinematic;
	public MessageBuilder(Message message, Cinematic cinematic) {
		this.message = message;
		this.cinematic = cinematic;
	}
	
	public void buildMessage(MetadataPo metadata, FieldTranslator translator) throws IOException {
		if(metadata.version.getVersionFloat() >= 1.18f) {
			translator.unsigned32(message.instructionsIndex);
			translator.unsigned32(message.hintsIndex);
			translator.unsigned32(message.victoryIndex);
			translator.unsigned32(message.lossIndex);
			translator.unsigned32(message.historyIndex);
			if(metadata.version.getVersionFloat() >= 1.22f)
				translator.unsigned32(message.scoutsIndex);
		}
		
		translator.string16(message.instructions);
		translator.string16(message.hints);
		translator.string16(message.victory);
		translator.string16(message.loss);
		translator.string16(message.history);
		if(metadata.version.getVersionFloat() >= 1.22f)
			translator.string16(message.scouts);
	}
	
	public void buildCinematic(MetadataPo metadata, FieldTranslator translator) throws IOException {
		translator.string16(cinematic.pregame);
		translator.string16(cinematic.victory);
		translator.string16(cinematic.loss);
		translator.string16(cinematic.background);
		
		translator.bool32(cinematic.bitmapIncluded);
		translator.unsigned32(cinematic.width);
		translator.unsigned32(cinematic.height);
		
		Wrapper<Short> shouldParse = new Wrapper<Short>((short) (cinematic.bitmapIncluded.getValue()? -1 : 1));
		translator.signed16(shouldParse);
		if(shouldParse.getValue() != 1) {
			// readBitmap
		}
	}
}
