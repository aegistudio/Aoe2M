package net.aegistudio.aoe2m;

import net.aegistudio.aoe2m.command.Command;

/**
 * Core model is not accessed by view but 
 * rather extensions.
 * 
 * An extension will receive instance of
 * this model, to register their own commands,
 * update current editing document, etc.
 * 
 * @author aegistudio
 */

public interface CoreModel {
	/**
	 * Register a command for execution.
	 */
	public void register(String name, Command command);
	
	/**
	 * Perform an action and push it to historic tree.
	 */
	public void perform(Action action);
	
	/**
	 * 
	 */
	public Reaction reaction();
	
	/**
	 * Require another core extension as dependency.
	 * Please notice that will cause model to break
	 * down if requirement unsatisfied.
	 */
	public <C extends CoreExtension> C require(Class<C> type)
		throws Aoe2mException;
}