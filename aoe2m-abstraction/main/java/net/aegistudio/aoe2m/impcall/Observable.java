package net.aegistudio.aoe2m.impcall;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Observable<T> {
	private final Set<WeakReference<Consumer<T>>> consumers = new HashSet<>();
	
	public void add(Consumer<T> consumer) {
		consumers.add(new WeakReference<>(consumer));
	}
	
	public void remove(Consumer<T> consumer) {
		consumers.remove(new WeakReference<>(consumer));
	}
	
	public void fire(T t) {
		consumers.forEach(c -> { if(c.get() != null) c.get().accept(t); });
	}
}
