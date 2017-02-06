package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;

public class EndLine extends Command {
	public EndLine(byte opcode) {
		super(opcode);
	}

	@Override
	public void translate(FieldTranslator translator) 
			throws IOException, CorruptionException {
		
	}

	@Override
	public void draw(ImagePrinter printer) {
		printer.endl();
	}
}
