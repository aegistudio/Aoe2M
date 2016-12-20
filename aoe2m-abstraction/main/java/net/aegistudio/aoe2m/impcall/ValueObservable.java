package net.aegistudio.aoe2m.impcall;

import java.util.function.Consumer;

public class ValueObservable<T> implements ValueObserver<T> {
	private Observable<T> observer = new Observable<T>();
	private T value = null;

	public void update(T value) {
		if(compare(this.value, value)) return;
		this.value = value;
		observer.fire(value);
	}

	private boolean compare(T oldV, T newV) {
		if(oldV == null && newV != null) return false;
		if(oldV != null && newV == null) return false;
		if(oldV == null && oldV == null) return true;
		return oldV.equals(newV);
	}
	
	@Override
	public void insert(Consumer<T> reactor) {
		observer.add(reactor);
	}

	@Override
	public void remove(Consumer<T> reactor) {
		observer.remove(reactor);
	}
	
	@Override
	public T current() {	return value;	}
}
