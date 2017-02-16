package net.aegistudio.aoe2m.scx.msg;

import java.io.IOException;

import net.aegistudio.uio.wrap.BooleanContainer;
import net.aegistudio.uio.wrap.Container;
import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
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
		
		translator.string16(message.instructions.stringWrapper());
		translator.string16(message.hints.stringWrapper());
		translator.string16(message.victory.stringWrapper());
		translator.string16(message.loss.stringWrapper());
		translator.string16(message.history.stringWrapper());
		if(metadata.version.getVersionFloat() >= 1.22f)
			translator.string16(message.scouts.stringWrapper());
	}
	
	public void buildCinematic(MetadataPo metadata, Translator translator) throws IOException, CorruptException {
		translator.string16(cinematic.pregame.stringWrapper());
		translator.string16(cinematic.victory.stringWrapper());
		translator.string16(cinematic.loss.stringWrapper());
		translator.string16(cinematic.background.stringWrapper());

		BooleanContainer bitmapIncluded = new BooleanContainer(cinematic.bitmap.get() != null);
		Wrapper<Short> shouldParse = new Container<>((short) (bitmapIncluded.get()? -1 : 1));
		
		Wrapper<Long> width = new Container<>(cinematic.bitmap.get() == null? 
				0l : cinematic.bitmap.get().getWidth());
		Wrapper<Long> height = new Container<>(cinematic.bitmap.get() == null? 
				0l : cinematic.bitmap.get().getHeight());
		
		translator.signed32(bitmapIncluded.bool32());
		translator.unsigned32(width);
		translator.unsigned32(height);
		
		translator.signed16(shouldParse);
		if(shouldParse.get() != 1) 
			translator.fallback(new ImageFallback(cinematic.bitmap));
	}
}
