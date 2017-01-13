package net.aegistudio.aoe2m.scx.msg;

import net.aegistudio.aoe2m.Text;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.StringFormater;

public class Message {
	public Wrapper<Text> instructions = new Wrapper<Text>(new Text());
	public Wrapper<Long> instructionsIndex = new Wrapper<Long>(-1l);
	public Wrapper<Text> hints = new Wrapper<Text>(new Text());
	public Wrapper<Long> hintsIndex = new Wrapper<Long>(-1l);
	public Wrapper<Text> victory = new Wrapper<Text>(new Text());
	public Wrapper<Long> victoryIndex = new Wrapper<Long>(-1l);
	public Wrapper<Text> loss = new Wrapper<Text>(new Text());
	public Wrapper<Long> lossIndex = new Wrapper<Long>(-1l);
	public Wrapper<Text> history = new Wrapper<Text>(new Text());
	public Wrapper<Long> historyIndex = new Wrapper<Long>(-1l);
	
	// version >= 1.22
	public Wrapper<Text> scouts = new Wrapper<Text>(new Text(0, ""));
	public Wrapper<Long> scoutsIndex = new Wrapper<Long>(-1l);
	public Wrapper<Integer> shouldParse = new Wrapper<Integer>(1);
	
	public String toString() {
		StringFormater toString = new StringFormater(this);
		toString.add("InstructionsIndex", instructionsIndex.getValue());
		toString.add("Instructions", instructions.getValue());
		toString.line();

		toString.add("HintsIndex", hintsIndex.getValue());
		toString.add("Hints", hints.getValue());
		toString.line();
		
		toString.add("VictoryIndex", victoryIndex.getValue());
		toString.add("Victory", victory.getValue());
		toString.line();

		toString.add("LossIndex", lossIndex.getValue());
		toString.add("Loss", loss.getValue());
		toString.line();
		
		toString.add("HistoryIndex", historyIndex.getValue());
		toString.add("History", history.getValue());
		toString.line();
		
		toString.add("ScoutsIndex", scoutsIndex.getValue());
		toString.add("Scouts", scouts.getValue());
		toString.line();
		
		toString.add("shouldParse", shouldParse.getValue());
		
		return toString.toString();
	}
}
