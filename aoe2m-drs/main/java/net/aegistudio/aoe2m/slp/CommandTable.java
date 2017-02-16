package net.aegistudio.aoe2m.slp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.slp.cmd.EndLine;
import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class CommandTable {
	public class Item {
		public Command command = commandSet.parse((byte)0); 
				
		public void translate(Translator translator) 
				throws IOException, CorruptException {
			
			Wrapper<Byte> opcode = new Container<>(command.opcode);
			translator.signed8(opcode);
			
			if(opcode.get() != command.opcode) 
				command = commandSet.parse(opcode.get());
			
			command.translate(translator);
		}
	}
	
	public Item newItem() {	return new Item();	}
	
	public class ScanLine {
		public final Wrapper<Integer> offset = Container.int0();

		public final List<Item> commands = new ArrayList<>();
		
		public void offset(Translator translator) throws IOException {
			translator.signed32(offset);
		}
		
		public void body(Translator translator)
				throws IOException, CorruptException {
			
			for(int i = 0; ; i ++) {
				Item command;
				if(i < commands.size()) command = commands.get(i);
				else commands.add(command = newItem());
				
				command.translate(translator);
				
				if(command.command instanceof EndLine) return;
			}
		}
	}
	
	public CommandSet commandSet = new DefaultCommandSet();
	
	public final List<ScanLine> lines = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(int height, Translator translator) 
			throws IOException, CorruptException  {
		
		translator.array(height, lines, 
				ScanLine::new, 
				ScanLine::offset,
				ScanLine::body);
	}
	
	public void render(ImagePrinter printer) {
		lines.forEach(scanline -> 
			scanline.commands.forEach(
					item -> item.command.draw(printer)));
	}
}
