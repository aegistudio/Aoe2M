package net.aegistudio.aoe2m.scx.msg;

import net.aegistudio.aoe2m.scx.String16;
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
}
