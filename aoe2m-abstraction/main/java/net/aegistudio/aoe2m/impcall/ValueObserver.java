package net.aegistudio.aoe2m.impcall;

import java.util.function.Consumer;

// Called on reference or value update.
public interface ValueObserver<Type> {
	public void insert(Consumer<Type> reactor);
	
	public void remove(Consumer<Type> reactor);
	
	public void update(Type value);
	
	public Type current();
}
