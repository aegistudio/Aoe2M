package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

public class TestMessageModified extends ScenarioReadBase {
	public TestMessageModified() {super("/messageModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		assertEquals(scenario.message.instructions.get().string, "Instruction\0");
		assertEquals(scenario.message.hints.get().string, "Tips\0");
		assertEquals(scenario.message.victory.get().string, "Victory\0");
		assertEquals(scenario.message.loss.get().string, "Defeat\0");
		assertEquals(scenario.message.scouts.get().string, "Scout\0");
	}
}
