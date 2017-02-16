package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.slp.Command;
import net.aegistudio.aoe2m.slp.ImagePrinter;
import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

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
			throws IOException, CorruptException {
		int count = pixelCount.pixelCount(
				opcode, translator, indices.size());
		translator.array(count, indices, 
				Container::byte0, Translator.squeech(translator::signed8));
	}

	@Override
	public void draw(ImagePrinter printer) {
		for(Wrapper<Byte> index : indices)
			todo.accept(printer, index.get());
	}
}
