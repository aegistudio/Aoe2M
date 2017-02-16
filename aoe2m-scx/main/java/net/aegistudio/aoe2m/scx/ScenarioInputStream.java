package net.aegistudio.aoe2m.scx;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import net.aegistudio.aoe2m.scx.meta.MetadataBuilder;
import net.aegistudio.uio.stream.*;
import net.aegistudio.uio.strmdbg.*;
import net.aegistudio.uio.*;

public class ScenarioInputStream extends InputStream {
	private final InputStream inputStream;
	private final String charset;
	
	public ScenarioInputStream(InputStream inputStream, String charset) {
		this.inputStream = inputStream;
		this.charset = charset;
	}
	
	@Override
	public int read() throws IOException {
		return this.inputStream.read();
	}
	
	private static final boolean debugging = false;
	
	public void readScenario(Scenario scenario) throws IOException, CorruptException {
		BinaryInputStream fieldInputStream = new BinaryInputStream(this, charset);
		new MetadataBuilder(scenario.metadata, scenario.globalVictory)
			.readUncompressedHeader(fieldInputStream, scenario.message);
		
		Inflater inflater = new Inflater(true);
		InflaterInputStream inflateInput = new InflaterInputStream(fieldInputStream, inflater);
		
		Translator translator;
		if(debugging) {
			DebugInputStream input = new DebugInputStream(inflateInput, System.out);
			translator = new InputTranslator(input, charset);
			translator = new StackTranslator(input, translator);
		}
		else translator = new InputTranslator(inflateInput, charset);
		
		new ScenarioDirector().build(scenario, translator);
	}
	
	public Scenario readScenario() throws Exception {
		Scenario scenario = new Scenario();
		readScenario(scenario);
		return scenario;
	}
}
