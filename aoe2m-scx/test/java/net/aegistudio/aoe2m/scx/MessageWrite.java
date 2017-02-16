package net.aegistudio.aoe2m.scx;

public class MessageWrite extends ScenarioWriteBase {

	public MessageWrite() {	super("messageWrite.scx");	}

	@Override
	public void todo(Scenario scenario) {
		scenario.message.instructions.set(new Text("Aoe2M Test Instruction!"));
		scenario.message.hints.set(new Text("Aoe2M Test Hint!"));
		scenario.message.victory.set(new Text("Aoe2M Test Victory!"));
		scenario.message.loss.set(new Text("Aoe2M Test Loss!"));
		scenario.message.history.set(new Text("Aoe2M Test History!"));
		scenario.message.scouts.set(new Text("Aoe2M Test Scouts!"));
	}
}
