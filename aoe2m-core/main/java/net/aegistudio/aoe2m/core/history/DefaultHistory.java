package net.aegistudio.aoe2m.core.history;

import java.util.Stack;

import net.aegistudio.aoe2m.Action;
import net.aegistudio.aoe2m.Aoe2mException;

public class DefaultHistory implements History {
	private final Stack<Action> redo = new Stack<Action>();
	private final Stack<Action> undo = new Stack<Action>();
	
	@Override
	public void perform(Action action) throws Aoe2mException {
		action.execute();
		undo.push(action);
		redo.clear();
	}

	@Override
	public Action undoTop() {
		if(undo.isEmpty()) return null;
		return undo.peek();
	}

	@Override
	public Action redoTop() {
		if(redo.isEmpty()) return null;
		return redo.peek();
	}

	@Override
	public void undo() throws Aoe2mException {
		Action undoTop = undoTop();
		if(undoTop == null) return;
		undoTop.undo();
		redo.push(undo.pop());
	}

	@Override
	public void redo() throws Aoe2mException {
		Action redoTop = redoTop();
		if(redoTop == null) return;
		redoTop.execute();
		
		
		undo.push(redo.pop());
	}
}
