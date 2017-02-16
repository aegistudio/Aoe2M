package net.aegistudio.aoe2m.slp;

import java.io.IOException;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;

public abstract class Command {
	public byte opcode;
	
	public Command(byte opcode) {
		this.opcode = opcode;
	}
	
	public abstract void translate(Translator translator)
			throws IOException, CorruptException;
	
	public abstract void draw(ImagePrinter printer);
}
