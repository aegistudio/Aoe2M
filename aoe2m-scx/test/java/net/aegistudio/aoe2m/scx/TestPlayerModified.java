package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.assertEquals;

import net.aegistudio.aoe2m.scx.player.EnumCivilization;

public class TestPlayerModified extends ScenarioReadBase {
	public TestPlayerModified() {super("/playerModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		assertEquals(scenario.metadata.playerCount, 2);
		assertEquals(scenario.player.playerData[0].initFood.get(), 100L);
		assertEquals(scenario.player.playerData[0]
				.civilization.get(), EnumCivilization.PERSIANS);
		assertEquals(scenario.player.playerData[0].getAsciiPlayerName(), "Test");
	}
}
