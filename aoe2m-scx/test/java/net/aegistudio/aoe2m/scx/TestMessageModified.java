package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

public class TestMessageModified extends TestScenarioBase {
	public TestMessageModified() {super("/messageModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		assertEquals(scenario.getMessage().instructions.getValue().toString(), "Instruction\\0");
		assertEquals(scenario.getMessage().hints.getValue().toString(), "Tips\\0");
		assertEquals(scenario.getMessage().victory.getValue().toString(), "Victory\\0");
		assertEquals(scenario.getMessage().loss.getValue().toString(), "Defeat\\0");
		assertEquals(scenario.getMessage().scouts.getValue().toString(), "Scout\\0");
	}
}
