package net.aegistudio.aoe2m.scx.input;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.ScenarioDirector;

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
		scenario.getMetadataBuilder().readUncompressedHeader(fieldInputStream);
		
		Inflater inflater = new Inflater(true);
		InflaterInputStream inflateInput = new InflaterInputStream(fieldInputStream, inflater);
		fieldInputStream = new FieldInputStream(inflateInput, charset);
		
		FieldInputTranslator translator = new FieldInputTranslator(fieldInputStream);
		new ScenarioDirector().build(scenario, translator);
		return scenario;
	}	
}
