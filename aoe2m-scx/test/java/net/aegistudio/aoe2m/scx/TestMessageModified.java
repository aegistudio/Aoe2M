package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

public class TestMessageModified extends TestScenarioBase {
	public TestMessageModified() {super("/messageModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		assertEquals(scenario.message.instructions.getValue().toString(), "Instruction\\0");
		assertEquals(scenario.message.hints.getValue().toString(), "Tips\\0");
		assertEquals(scenario.message.victory.getValue().toString(), "Victory\\0");
		assertEquals(scenario.message.loss.getValue().toString(), "Defeat\\0");
		assertEquals(scenario.message.scouts.getValue().toString(), "Scout\\0");
	}
}
