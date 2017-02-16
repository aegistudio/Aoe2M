package net.aegistudio.aoe2m.slp;

import java.io.IOException;

import net.aegistudio.uio.*;
import net.aegistudio.uio.ra.RandomAccessible;
import net.aegistudio.uio.wrap.*;

public class Frame {
	public final Wrapper<Long> commandOffset = Container.long0();
	
	public final Wrapper<Long> outlineOffset = Container.long0();
	
	public final Wrapper<Long> paletteOffset = Container.long0();
	
	public final Wrapper<Integer> properties = Container.int0();
	
	public final Wrapper<Integer> width = Container.int0();
	
	public final Wrapper<Integer> height = Container.int0();
	
	public final Wrapper<Integer> originX = Container.int0();
	
	public final Wrapper<Integer> originY = Container.int0();
	
	public void translate(Translator translator) throws IOException {
		translator.unsigned32(commandOffset);
		translator.unsigned32(outlineOffset);
		translator.unsigned32(paletteOffset);
		
		translator.signed32(properties);
		
		translator.signed32(width);
		translator.signed32(height);
		translator.signed32(originX);
		translator.signed32(originY);
	}
	
	public void seek(Translator translator, RandomAccessible access, 
			Outline outline, CommandTable command) throws IOException, CorruptException {
		int height = this.height.get();
		
		access.seek(outlineOffset.get());
		outline.translate(height, translator);
		
		access.seek(commandOffset.get());
		command.translate(height, translator);
	}
}