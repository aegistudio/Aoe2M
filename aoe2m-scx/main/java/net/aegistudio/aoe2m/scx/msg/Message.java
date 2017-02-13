package net.aegistudio.aoe2m.scx.msg;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.StringFormater;
import net.aegistudio.aoe2m.scx.TextContainer;

public class Message {
	public TextContainer instructions = new TextContainer();
	public Wrapper<Long> instructionsIndex = new Container<Long>(-1l);
	public TextContainer hints = new TextContainer();
	public Wrapper<Long> hintsIndex = new Container<Long>(-1l);
	public TextContainer victory = new TextContainer();
	public Wrapper<Long> victoryIndex = new Container<Long>(-1l);
	public TextContainer loss = new TextContainer();
	public Wrapper<Long> lossIndex = new Container<Long>(-1l);
	public TextContainer history = new TextContainer();
	public Wrapper<Long> historyIndex = new Container<Long>(-1l);
	
	// version >= 1.22
	public TextContainer scouts = new TextContainer();
	public Wrapper<Long> scoutsIndex = new Container<Long>(-1l);
	public Wrapper<Integer> shouldParse = new Container<Integer>(1);
	
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
