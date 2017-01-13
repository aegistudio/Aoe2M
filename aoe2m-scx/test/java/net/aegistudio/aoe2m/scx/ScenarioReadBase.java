package net.aegistudio.aoe2m.scx;

import java.io.InputStream;

import org.junit.Test;

import net.aegistudio.aoe2m.io.ScenarioInputStream;

import static org.junit.Assert.*;

public abstract class ScenarioReadBase {
	protected final String scenarioName;
	protected final String encoding;
	public ScenarioReadBase(String scenarioName, String encoding) {
		this.scenarioName = scenarioName;
		this.encoding = encoding;
	}
	
	public final @Test void test() {
		try (	InputStream fileInputStream = getClass().getResourceAsStream(scenarioName);
				ScenarioInputStream scenarioInputStream = new ScenarioInputStream(fileInputStream, encoding);) {
			
			assertNotNull(fileInputStream);
			Scenario scenario = scenarioInputStream.readScenario();
			todo(scenario);
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue("Error while reading file!", false);
		}
	}
	
	protected abstract void todo(Scenario scenario);
}
