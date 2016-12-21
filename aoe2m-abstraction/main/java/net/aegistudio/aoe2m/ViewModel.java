package net.aegistudio.aoe2m;

import java.util.List;

import net.aegistudio.aoe2m.command.CommandParseur;
import net.aegistudio.aoe2m.impcall.MapObserver;
import net.aegistudio.aoe2m.impcall.ValueObserver;

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
	/** Execute a command. */
	public void execute(String command);
	
	/** Tab-complete a command. */
	public List<String> complete(String command);

	/** Observe current selected document. */
	public ValueObserver<Document> current();
	
	/** Observe all opened document **/
	public MapObserver<String, Document> all();
	
	public CommandParseur parseur();
}
