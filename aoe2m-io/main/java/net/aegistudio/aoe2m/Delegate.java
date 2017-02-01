package net.aegistudio.aoe2m;

import java.lang.ref.WeakReference;

public class Delegate<E> implements Wrapper<E> {
	protected final WeakReference<Wrapper<E>> delegated;
	public Delegate(Wrapper<E> delegated) {
		this.delegated = new WeakReference<Wrapper<E>>(delegated);
	}
	
	@Override
	public void setValue(E value) {
		delegated.get().setValue(value);
	}

	@Override
	public E getValue() {
		return delegated.get().getValue();
	}
}