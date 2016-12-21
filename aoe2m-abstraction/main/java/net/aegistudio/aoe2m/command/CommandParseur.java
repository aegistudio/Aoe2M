package net.aegistudio.aoe2m.command;

import net.aegistudio.aoe2m.Aoe2mException;

public interface CommandParseur {
	public String encode(String parameter) throws Aoe2mException;
	
	public String[] parse(String command) throws Aoe2mException;
}
