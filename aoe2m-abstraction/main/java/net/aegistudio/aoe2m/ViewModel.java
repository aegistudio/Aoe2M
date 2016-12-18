package net.aegistudio.aoe2m;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * CoreModel is a modal / stateful server that 
 * react with front end with command and reactor.
 * 
 * Command are atomic operations in the system.
 * The user do some operation and then will be
 * converted to a command.
 * 
 * Reactor are event-style response in the system.
 * Every time when something changed, it will cause
 * the reactor to fire and notify changes.
 * 
 * @author aegistudio
 */

public interface ViewModel {
	/**
	 * Execute a command.
	 */
	public void execute(String command);
	
	/**
	 * Tab-complete a command.
	 */
	public List<String> complete(String command);

	/**
	 * Add reactor to a key.
	 * Please notice that the key supports
	 * fuzzy matching, allowing subscription to
	 * a set of values.
	 */
	public <T> void add(String key, BiConsumer<String, T> reactor);

	/**
	 * Remove the registered reactor.
	 */
	public <T> void remove(String key, BiConsumer<String, T> reactor);
}
