package net.aegistudio.aoe2m.scx;

/**
 * This file assists scenario creation,
 * by reading data from a preset scenario
 * file, and replace it with your file name.
 * 
 * @author aegistudio
 */

public class EmptyScenario extends Scenario {
	public EmptyScenario(String name) {
		try {
			ScenarioInputStream internalInputStream = new ScenarioInputStream(
					getClass().getResourceAsStream("/empty.scx"), "gbk");
			internalInputStream.readScenario(this);
			internalInputStream.close();
		}
		catch(Exception e) {
			throw new Error();
		}
		
		super.metadata.originalFileName.set(new Text(name));
		super.metadata.touch();
	}
}
