package net.aegistudio.aoe2m.scx.input;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.ScenarioDirector;
import net.aegistudio.aoe2m.scx.meta.MetadataBuilder;

public class ScenarioOutputStream extends OutputStream {
	private final OutputStream outputStream;
	public ScenarioOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void close() throws IOException {
		this.outputStream.close();
	}
	
	@Override
	public void write(int b) throws IOException {
		this.outputStream.write(b);
	}

	private static final boolean debugging = false;
	
	public void writeScenario(Scenario scenario) throws IOException, CorruptionException {
		FieldOutputStream fieldOutputStream = new FieldOutputStream(this);
		new MetadataBuilder(scenario.metadata, scenario.globalVictory)
			.writeUncompressedHeader(fieldOutputStream, scenario.message);
		
		Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION, true);
		DeflaterOutputStream deflateOutput = new DeflaterOutputStream(fieldOutputStream, deflater);
		
		FieldTranslator translator;
		if(debugging) {
			DebugOutputStream input = new DebugOutputStream(deflateOutput, System.out);
			translator = new FieldOutputTranslator(input);
			translator = new StackDebugTranslator(input, translator);
		}
		else translator = new FieldOutputTranslator(deflateOutput);
		
		new ScenarioDirector().build(scenario, translator);
		
		deflateOutput.finish();
	}
}
