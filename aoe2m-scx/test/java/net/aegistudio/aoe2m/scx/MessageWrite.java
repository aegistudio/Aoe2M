package net.aegistudio.aoe2m.scx;

import net.aegistudio.aoe2m.Text;

public class MessageWrite extends ScenarioWriteBase {

	public MessageWrite() {	super("messageWrite.scx");	}

	@Override
	public void todo(Scenario scenario) {
		scenario.message.instructions.setValue(new Text("Aoe2M Test Instruction!"));
		scenario.message.hints.setValue(new Text("Aoe2M Test Hint!"));
		scenario.message.victory.setValue(new Text("Aoe2M Test Victory!"));
		scenario.message.loss.setValue(new Text("Aoe2M Test Loss!"));
		scenario.message.history.setValue(new Text("Aoe2M Test History!"));
		scenario.message.scouts.setValue(new Text("Aoe2M Test Scouts!"));
	}

}
