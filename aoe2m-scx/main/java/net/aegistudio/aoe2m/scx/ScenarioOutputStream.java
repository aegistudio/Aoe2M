package net.aegistudio.aoe2m.scx;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.stream.*;
import net.aegistudio.uio.strmdbg.DebugOutputStream;
import net.aegistudio.uio.strmdbg.StackTranslator;
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
	
	public void writeScenario(Scenario scenario) throws IOException, CorruptException {
		BinaryOutputStream fieldOutputStream = new BinaryOutputStream(this);
		new MetadataBuilder(scenario.metadata, scenario.globalVictory)
			.writeUncompressedHeader(fieldOutputStream, scenario.message);
		
		Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION, true);
		DeflaterOutputStream deflateOutput = new DeflaterOutputStream(fieldOutputStream, deflater);
		
		Translator translator;
		if(debugging) {
			DebugOutputStream input = new DebugOutputStream(deflateOutput, System.out);
			translator = new OutputTranslator(input);
			translator = new StackTranslator(input, translator);
		}
		else translator = new OutputTranslator(deflateOutput);
		
		new ScenarioDirector().build(scenario, translator);
		
		deflateOutput.finish();
	}
}
