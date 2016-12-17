package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

public class TestUnitModified extends TestScenarioBase {
	public TestUnitModified() {	super("/unitModified.scx", "gbk");	}

	@Override
	protected void todo(Scenario scenario) {
		assertEquals(scenario.map.gaia.size(), 1);
		assertEquals(scenario.map.units[0].size(), 6);
		assertEquals(scenario.map.units[1].size(), 1);
	}
}
