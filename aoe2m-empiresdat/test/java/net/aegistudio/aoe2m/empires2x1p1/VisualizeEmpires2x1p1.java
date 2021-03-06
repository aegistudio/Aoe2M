package net.aegistudio.aoe2m.empires2x1p1;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import org.junit.Test;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.stream.InputTranslator;
import net.aegistudio.uio.strmdbg.DebugInputStream;

//import net.aegistudio.aoe2m.io.StackDebugTranslator;

public class VisualizeEmpires2x1p1 {
	public @Test void test() throws IOException, CorruptException {
		Inflater inflater = new Inflater(true);
		try(	InputStream input = getClass().getResourceAsStream("/empires2_x1_p1.dat");
				InflaterInputStream inflateInput = new InflaterInputStream(input, inflater, 15);
				DebugInputStream debugInput = new DebugInputStream(inflateInput, System.err)) {
			
			//FieldTranslator inputTranslator = new StackDebugTranslator(
			//		debugInput, new FieldInputTranslator(debugInput, "gbk"));
	
			Translator inputTranslator 
				= new InputTranslator(inflateInput, "gbk");
			
			Empires2x1p1 empires2x1p1 = new Empires2x1p1();
			empires2x1p1.translate(inputTranslator);
		}
	}
}
