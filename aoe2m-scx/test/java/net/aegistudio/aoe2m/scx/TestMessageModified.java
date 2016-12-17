package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

public class TestMessageModified extends ScenarioReadBase {
	public TestMessageModified() {super("/messageModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		assertEquals(scenario.message.instructions, "Instruction\0");
		assertEquals(scenario.message.hints, "Tips\0");
		assertEquals(scenario.message.victory, "Victory\0");
		assertEquals(scenario.message.loss, "Defeat\0");
		assertEquals(scenario.message.scouts, "Scout\0");
	}
}
