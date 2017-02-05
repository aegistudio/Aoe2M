package net.aegistudio.aoe2m.slp;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.ra.RandomAccessible;

public class Frame {
	public final Wrapper<Long> commandOffset = Container.long0();
	
	public final Wrapper<Long> outlineOffset = Container.long0();
	
	public final Wrapper<Long> paletteOffset = Container.long0();
	
	public final Wrapper<Integer> properties = Container.int0();
	
	public final Wrapper<Integer> width = Container.int0();
	
	public final Wrapper<Integer> height = Container.int0();
	
	public final Wrapper<Integer> originX = Container.int0();
	
	public final Wrapper<Integer> originY = Container.int0();
	
	public void translate(FieldTranslator translator) throws IOException {
		translator.unsigned32(commandOffset);
		translator.unsigned32(outlineOffset);
		translator.unsigned32(paletteOffset);
		
		translator.signed32(properties);
		
		translator.signed32(width);
		translator.signed32(height);
		translator.signed32(originX);
		translator.signed32(originY);
	}
	
	public void seek(FieldTranslator translator, RandomAccessible access, 
			Outline outline, CommandTable command) throws IOException, CorruptionException {
		int height = this.height.getValue();
		
		access.seek(outlineOffset.getValue());
		outline.translate(height, translator);
		
		access.seek(commandOffset.getValue());
		command.translate(height, translator);
	}
}