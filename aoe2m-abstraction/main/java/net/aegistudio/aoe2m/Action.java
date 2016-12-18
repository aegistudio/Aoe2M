package net.aegistudio.aoe2m;

/**
 * An action is a special kind of operation 
 * that allows undo / redo.
 * 
 * @author aegistudio
 */

public interface Action {
	/**
	 * @return an unlocalized string of the action.
	 */
	public String describe();
	
	/**
	 * Perform / redo the action.
	 */
	public void execute() throws Aoe2mException;
	
	/**
	 * Undo the action.
	 */
	public void undo() throws Aoe2mException;
}
