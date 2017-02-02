package net.aegistudio.aoe2m.empires2x1p1.unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class UnitHeader {
	public Wrapper<Byte> exists = new Container<>((byte)0);
	
	public List<UnitCommand> unitCommands = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.signed8(exists);
		if(exists.getValue() != 0) {
			Wrapper<Integer> commandCount = 
					new Container<>(unitCommands.size());
			translator.unsigned16(commandCount);
			translator.array(commandCount.getValue(), unitCommands, 
					UnitCommand::new, wrap(translator, UnitCommand::translate));
		}
	}
}
