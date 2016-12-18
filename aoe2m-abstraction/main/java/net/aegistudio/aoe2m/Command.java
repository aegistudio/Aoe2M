package net.aegistudio.aoe2m;

import java.util.List;

/**
 * An atomic operation in the system.
 * 
 * @author aegistudio
 */

public interface Command {
	/**
	 * Execute the command denoted by the system.
	 */
	public void execute(String[] parameter) throws Aoe2mException;
	
	/**
	 * Complete the last parameter of the command.
	 */
	public List<String> complete(String[] parameter);
}
