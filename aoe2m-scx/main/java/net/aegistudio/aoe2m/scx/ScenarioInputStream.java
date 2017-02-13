package net.aegistudio.aoe2m.scx;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.io.DebugInputStream;
import net.aegistudio.aoe2m.io.FieldInputStream;
import net.aegistudio.aoe2m.io.FieldInputTranslator;
import net.aegistudio.aoe2m.io.StackDebugTranslator;
import net.aegistudio.aoe2m.scx.meta.MetadataBuilder;

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
	
	public void readScenario(Scenario scenario) throws IOException, CorruptionException {
		FieldInputStream fieldInputStream = new FieldInputStream(this, charset);
		new MetadataBuilder(scenario.metadata, scenario.globalVictory)
			.readUncompressedHeader(fieldInputStream, scenario.message);
		
		Inflater inflater = new Inflater(true);
		InflaterInputStream inflateInput = new InflaterInputStream(fieldInputStream, inflater);
		
		Translator translator;
		if(debugging) {
			DebugInputStream input = new DebugInputStream(inflateInput, System.out);
			translator = new FieldInputTranslator(input, charset);
			translator = new StackDebugTranslator(input, translator);
		}
		else translator = new FieldInputTranslator(inflateInput, charset);
		
		new ScenarioDirector().build(scenario, translator);
	}
	
	public Scenario readScenario() throws Exception {
		Scenario scenario = new Scenario();
		readScenario(scenario);
		return scenario;
	}
}
