package net.aegistudio.aoe2m.slp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.slp.cmd.EndLine;

import static net.aegistudio.aoe2m.TranslateWrapper.wrapAll;

public class CommandTable {
	public class Item {
		public Command command = commandSet.parse((byte)0); 
				
		public void translate(FieldTranslator translator) 
				throws IOException, CorruptionException {
			
			Wrapper<Byte> opcode = new Container<>(command.opcode);
			translator.signed8(opcode);
			
			if(opcode.getValue() != command.opcode) 
				command = commandSet.parse(opcode.getValue());
			
			command.translate(translator);
		}
	}
	
	public Item newItem() {	return new Item();	}
	
	public class ScanLine {
		public final Wrapper<Integer> offset = Container.int0();

		public final List<Item> commands = new ArrayList<>();
		
		public void offset(FieldTranslator translator) throws IOException {
			translator.signed32(offset);
		}
		
		public void body(FieldTranslator translator)
				throws IOException, CorruptionException {
			
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
	public void translate(int height, FieldTranslator translator) 
			throws IOException, CorruptionException  {
		
		translator.array(height, lines, 
				ScanLine::new, wrapAll(translator,
				ScanLine::offset,
				ScanLine::body));
	}
	
	public void render(ImagePrinter printer) {
		lines.forEach(scanline -> 
			scanline.commands.forEach(
					item -> item.command.draw(printer)));
	}
}