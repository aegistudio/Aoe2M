package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;
import java.util.function.Consumer;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;

public class Repeat extends Command {
	public final EnumPixelCount pixelCount;
	public final Consumer<ImagePrinter> task;
	public Repeat(byte opcode, EnumPixelCount pixelCount, Consumer<ImagePrinter> task) {
		super(opcode);
		this.pixelCount = pixelCount;
		this.task = task;
	}
	
	public Wrapper<Integer> count = Container.int0();
	
	@Override
	public void translate(Translator translator) 
			throws IOException, CorruptionException {
		count.setValue(pixelCount.pixelCount(
				opcode, translator, count.getValue()));
	}

	@Override
	public void draw(ImagePrinter printer) {
		for(int i = 0; i < count.getValue(); i ++)
			task.accept(printer);
	}
}
