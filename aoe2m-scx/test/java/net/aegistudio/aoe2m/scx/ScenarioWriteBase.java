package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;

import net.aegistudio.aoe2m.Text;

/**
 * This file will not be run in maven automated 
 * test unless you run it manually. You could
 * take the scenario file into the game and see
 * whether the scenario works.
 * 
 * @author aegistudio
 */

public abstract class ScenarioWriteBase {
	protected final String scenarioFile;
	public ScenarioWriteBase(String scenarioFile) {
		this.scenarioFile = scenarioFile;
	}
	
	public @Test void test() {
		try {
			Scenario scenario = new EmptyScenario(scenarioFile);
			
			scenario.metadata.originalFileName.setValue(new Text(scenarioFile));
			todo(scenario);
			
			File file = new File(scenarioFile);
			file.createNewFile();
			ScenarioOutputStream output = new ScenarioOutputStream(
					new FileOutputStream(file));
			
			output.writeScenario(scenario);
			output.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			assertTrue("Error while writing file.", false);
		}
	}
	
	public abstract void todo(Scenario scenario);
}
