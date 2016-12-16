package net.aegistudio.aoe2m.scx.msg;

import net.aegistudio.aoe2m.scx.String16;
import net.aegistudio.aoe2m.scx.StringFormater;
import net.aegistudio.aoe2m.scx.Wrapper;

public class Message {
	public Wrapper<String16> instructions = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<Long> instructionsIndex = new Wrapper<Long>(-1l);
	public Wrapper<String16> hints = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<Long> hintsIndex = new Wrapper<Long>(-1l);
	public Wrapper<String16> victory = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<Long> victoryIndex = new Wrapper<Long>(-1l);
	public Wrapper<String16> loss = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<Long> lossIndex = new Wrapper<Long>(-1l);
	public Wrapper<String16> history = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<Long> historyIndex = new Wrapper<Long>(-1l);
	
	// version >= 1.22
	public Wrapper<String16> scouts = new Wrapper<String16>(new String16(0, ""));
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
