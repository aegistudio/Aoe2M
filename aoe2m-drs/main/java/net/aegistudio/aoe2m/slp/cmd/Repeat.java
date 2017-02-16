package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;
import java.util.function.Consumer;

import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;
import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

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
			throws IOException, CorruptException {
		count.set(pixelCount.pixelCount(
				opcode, translator, count.get()));
	}

	@Override
	public void draw(ImagePrinter printer) {
		for(int i = 0; i < count.get(); i ++)
			task.accept(printer);
	}
}
