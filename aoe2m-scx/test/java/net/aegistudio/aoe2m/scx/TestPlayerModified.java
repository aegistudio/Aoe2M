package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.assertEquals;

import net.aegistudio.aoe2m.scx.player.EnumCivilization;

public class TestPlayerModified extends TestScenarioBase {
	public TestPlayerModified() {super("/playerModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		assertEquals(scenario.metadata.getMetadata().playerCount, 2);
		assertEquals(scenario.playerTable.getPlayerTable()[0].initFood.getValue(), 100L);
		assertEquals(scenario.playerTable.getPlayerTable()[0]
				.civilization.getValue(), EnumCivilization.PERSIANS);
		assertEquals(scenario.playerTable.getPlayerTable()[0].getAsciiPlayerName(), "Test");
	}
}
