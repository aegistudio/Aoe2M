package net.aegistudio.aoe2m.scx.msg;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class MessageBuilder {
	private final Message message;
	private final Cinematic cinematic;
	public MessageBuilder(Message message, Cinematic cinematic) {
		this.message = message;
		this.cinematic = cinematic;
	}
	
	public void buildMessage(MetadataPo metadata, Translator translator) throws IOException {
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
	
	public void buildCinematic(MetadataPo metadata, Translator translator) throws IOException, CorruptionException {
		translator.string16(cinematic.pregame);
		translator.string16(cinematic.victory);
		translator.string16(cinematic.loss);
		translator.string16(cinematic.background);

		Wrapper<Boolean> bitmapIncluded = new Container<>(cinematic.bitmap.getValue() != null);
		Wrapper<Short> shouldParse = new Container<>((short) (bitmapIncluded.getValue()? -1 : 1));
		
		Wrapper<Long> width = new Container<>(cinematic.bitmap.getValue() == null? 
				0l : cinematic.bitmap.getValue().getWidth());
		Wrapper<Long> height = new Container<>(cinematic.bitmap.getValue() == null? 
				0l : cinematic.bitmap.getValue().getHeight());
		
		translator.bool32(bitmapIncluded);
		translator.unsigned32(width);
		translator.unsigned32(height);
		
		translator.signed16(shouldParse);
		if(shouldParse.getValue() != 1) 
			translator.bitmap(cinematic.bitmap);
	}
}
