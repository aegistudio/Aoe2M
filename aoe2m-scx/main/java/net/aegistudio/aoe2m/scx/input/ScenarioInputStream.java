package net.aegistudio.aoe2m.scx.input;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.ScenarioDirector;
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
			.readUncompressedHeader(fieldInputStream);
		
		Inflater inflater = new Inflater(true);
		InflaterInputStream inflateInput = new InflaterInputStream(fieldInputStream, inflater);
		
		FieldTranslator translator;
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
