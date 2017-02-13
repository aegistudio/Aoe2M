package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;

public class Fill extends Command {
	public final EnumPixelCount pixelCount;
	public final BiConsumer<ImagePrinter, Byte> todo;
	
	public Fill(byte opcode, EnumPixelCount pixelCount, 
			BiConsumer<ImagePrinter, Byte> todo) {
		super(opcode);
		this.pixelCount = pixelCount;
		this.todo = todo;
	}

	public final Wrapper<Integer> count = Container.int0();
	public final Wrapper<Byte> pixel = Container.byte0();
	
	@Override
	public void translate(Translator translator) 
			throws IOException, CorruptionException {
		count.setValue(pixelCount.pixelCount(
				opcode, translator, count.getValue()));
		translator.signed8(pixel);
	}

	@Override
	public void draw(ImagePrinter printer) {
		for(int i = 0; i < count.getValue(); i ++)
			todo.accept(printer, pixel.getValue());
	}
}
