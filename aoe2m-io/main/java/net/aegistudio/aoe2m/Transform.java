package net.aegistudio.aoe2m;

import java.util.function.Function;

public class Transform<E, F> implements Wrapper<F> {
	public final Delegate<E> delegate;
	public final Function<E, F> outcast;
	public final Function<F, E> incast;
	
	public Transform(Wrapper<E> delegated, 
			Function<E, F> outcast, Function<F, E> incast) {
		delegate = new Delegate<E>(delegated);
		this.outcast = outcast;
		this.incast = incast;
	}

	public F getValue() {
		return outcast.apply(delegate.getValue());
	}

	@Override
	public void setValue(F value) {
		delegate.setValue(incast.apply(value));
	}
}
