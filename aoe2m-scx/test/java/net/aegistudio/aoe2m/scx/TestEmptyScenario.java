package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

public class TestEmptyScenario extends ScenarioReadBase {
	public TestEmptyScenario() { super("/emptyScenario.scx", "gbk");	}

	public void todo(Scenario scenario) {
		assertEquals(scenario.metadata.playerCount, 2);
	}
}
