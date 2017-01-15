package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

/**
 * As there'are cases map fails to read before,
 * we provide a complex random generated map for
 * test.
 * 
 * @author aegistudio
 */

public class TestRandomMapRead extends ScenarioReadBase {

	public TestRandomMapRead() {	super("/randomMap.scx", "gbk");		}

	@Override
	protected void todo(Scenario scenario) {
		assertEquals(scenario.metadata.playerCount, 2);
		assertEquals(scenario.map.gaia.element.size(), 1920);
		assertEquals(scenario.map.units[0].size(), 5);
		assertEquals(scenario.map.units[1].size(), 5);
	}

}
