package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;
import java.util.function.Consumer;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;

public class Once extends Command {
	public final Consumer<ImagePrinter> todo;
	public Once(byte opcode, Consumer<ImagePrinter> todo) {
		super(opcode);
		this.todo = todo;
	}

	@Override
	public void translate(Translator translator) throws IOException, CorruptionException {
		
	}

	@Override
	public void draw(ImagePrinter printer) {
		this.todo.accept(printer);
	}
}
