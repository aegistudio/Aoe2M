package net.aegistudio.aoe2m.slp;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;

public abstract class Command {
	public byte opcode;
	
	public Command(byte opcode) {
		this.opcode = opcode;
	}
	
	public abstract void translate(Translator translator)
			throws IOException, CorruptionException;
	
	public abstract void draw(ImagePrinter printer);
}
