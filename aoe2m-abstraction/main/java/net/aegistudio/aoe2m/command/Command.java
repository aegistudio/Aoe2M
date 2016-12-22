package net.aegistudio.aoe2m.command;

import java.util.List;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.Document;

/**
 * An atomic operation in the system.
 * 
 * @author aegistudio
 */

public interface Command {
	/**
	 * Execute the command denoted by the system.
	 */
	public void execute(Document document, String[] parameter) throws Aoe2mException;
	
	/**
	 * Complete the last parameter of the command.
	 */
	public List<String> complete(Document document, String[] parameter);
}
