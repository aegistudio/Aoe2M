package net.aegistudio.aoe2m.scx;

import java.io.InputStream;
import java.util.function.Consumer;

import org.junit.Test;
import static org.junit.Assert.*;

import net.aegistudio.aoe2m.scx.input.ScenarioInputStream;
import net.aegistudio.aoe2m.scx.player.EnumCivilization;

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

	public @Test void emptyScenario() {
		read("/emptyScenario.scx", "gbk", scenario -> {
			assertEquals(scenario.metadata.getMetadata().playerCount, 2);			
		});
	}
	
	public @Test void playerModified() {
		read("/playerModified.scx", "gbk", scenario -> {
			assertEquals(scenario.metadata.getMetadata().playerCount, 2);
			assertEquals(scenario.playerTable.getPlayerTable()[0].initFood.getValue(), 100L);
			assertEquals(scenario.playerTable.getPlayerTable()[0]
					.civilization.getValue(), EnumCivilization.PERSIANS);
			assertEquals(scenario.playerTable.getPlayerTable()[0].getAsciiPlayerName(), "Test");
		});
	}
}
