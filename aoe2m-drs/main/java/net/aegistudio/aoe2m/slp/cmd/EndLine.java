package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;

import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;
import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;

public class EndLine extends Command {
	public EndLine(byte opcode) {
		super(opcode);
	}

	@Override
	public void translate(Translator translator) 
			throws IOException, CorruptException {
		
	}

	@Override
	public void draw(ImagePrinter printer) {
		printer.endl();
	}
}
