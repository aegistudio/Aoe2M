package net.aegistudio.aoe2m.scx.input;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

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
	
	public Scenario readScenario() throws Exception {
		Scenario scenario = new Scenario();
		FieldInputStream fieldInputStream = new FieldInputStream(inputStream, charset);
		new MetadataBuilder(scenario.metadata, scenario.globalVictory)
			.readUncompressedHeader(fieldInputStream);
		
		Inflater inflater = new Inflater(true);
		InflaterInputStream inflateInput = new InflaterInputStream(fieldInputStream, inflater);
		
		FieldInputTranslator translator = new FieldInputTranslator(inflateInput, charset);
		//FieldInputTranslator translator = new StackDebugTranslator(
		//		new DebugInputStream(inflateInput, System.out), charset);
		new ScenarioDirector().build(scenario, translator);
		
		return scenario;
	}
}
