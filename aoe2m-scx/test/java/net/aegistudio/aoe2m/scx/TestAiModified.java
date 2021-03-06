package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.assertEquals;

public class TestAiModified extends ScenarioReadBase {
	public TestAiModified() {	super("/aiModified.scx", "gbk");	}

	@Override
	protected void todo(Scenario scenario) {
		assertEquals(scenario.player.playerData[1].aiName.get(), "test");
		assertEquals(scenario.player.playerData[1].perFile.get(), 
				"(defrule\n    (true)\n    =>\n    (resign)\n)\n");
	}
}
