package net.aegistudio.aoe2m.scx;

import java.io.InputStream;
import java.util.function.Consumer;

import org.junit.Test;
import static org.junit.Assert.*;

import net.aegistudio.aoe2m.scx.input.ScenarioInputStream;

public class TestScenario {
	private void read(String scenarioName, String encoding, Consumer<Scenario> scenarioTodo) {
		try (	InputStream fileInputStream = getClass().getResourceAsStream(scenarioName);
				ScenarioInputStream scenarioInputStream = new ScenarioInputStream(fileInputStream, encoding);) {
			
			assertNotNull(fileInputStream);
			Scenario scenario = scenarioInputStream.readScenario();
			scenarioTodo.accept(scenario);
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue("Error while reading file!", false);
		}
	}

	public @Test void emptyScenarioRead() {
		read("/emptyScenario.scx", "gbk", scenario -> {
			assertEquals(scenario.metadata.getMetadata().playerCount, 2);			
		});
	}
}
