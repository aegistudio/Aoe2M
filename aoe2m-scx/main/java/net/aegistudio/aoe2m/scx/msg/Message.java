package net.aegistudio.aoe2m.scx.msg;

import net.aegistudio.uio.wrap.Container;
import net.aegistudio.uio.Wrapper;
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
		toString.add("InstructionsIndex", instructionsIndex.get());
		toString.add("Instructions", instructions.get());
		toString.line();

		toString.add("HintsIndex", hintsIndex.get());
		toString.add("Hints", hints.get());
		toString.line();
		
		toString.add("VictoryIndex", victoryIndex.get());
		toString.add("Victory", victory.get());
		toString.line();

		toString.add("LossIndex", lossIndex.get());
		toString.add("Loss", loss.get());
		toString.line();
		
		toString.add("HistoryIndex", historyIndex.get());
		toString.add("History", history.get());
		toString.line();
		
		toString.add("ScoutsIndex", scoutsIndex.get());
		toString.add("Scouts", scouts.get());
		toString.line();
		
		toString.add("shouldParse", shouldParse.get());
		
		return toString.toString();
	}
}
