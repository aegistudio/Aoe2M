package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;

public class BlockCopy extends Command {
	public final EnumPixelCount pixelCount;
	public final BiConsumer<ImagePrinter, Byte> todo;
	
	public BlockCopy(byte opcode, EnumPixelCount pixelCount, 
			BiConsumer<ImagePrinter, Byte> todo) {
		super(opcode);
		this.pixelCount = pixelCount;
		this.todo = todo;
	}

	public final List<Wrapper<Byte>> indices = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void translate(Translator translator) 
			throws IOException, CorruptionException {
		int count = pixelCount.pixelCount(
				opcode, translator, indices.size());
		translator.array(count, indices, 
				Container::byte0, translator::signed8);
	}

	@Override
	public void draw(ImagePrinter printer) {
		for(Wrapper<Byte> index : indices)
			todo.accept(printer, index.getValue());
	}
}
