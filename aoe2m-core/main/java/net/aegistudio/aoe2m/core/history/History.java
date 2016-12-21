package net.aegistudio.aoe2m.core.history;

import net.aegistudio.aoe2m.Action;
import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.core.PerformReactor;

public interface History extends PerformReactor {
	public Action undoTop();
	
	public Action redoTop();
	
	public void undo() throws Aoe2mException;
	
	public void redo() throws Aoe2mException;
}
