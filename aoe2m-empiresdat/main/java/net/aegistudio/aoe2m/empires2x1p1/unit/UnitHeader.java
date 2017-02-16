package net.aegistudio.aoe2m.empires2x1p1.unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class UnitHeader {
	public Wrapper<Byte> exists = new Container<>((byte)0);
	
	public List<UnitCommand> unitCommands = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.signed8(exists);
		if(exists.get() != 0) {
			Wrapper<Integer> commandCount = 
					new Container<>(unitCommands.size());
			translator.unsigned16(commandCount);
			translator.array(commandCount.get(), unitCommands, 
					UnitCommand::new, UnitCommand::translate);
		}
	}
}
