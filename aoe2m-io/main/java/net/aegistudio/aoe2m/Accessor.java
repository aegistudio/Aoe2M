package net.aegistudio.aoe2m;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public class Accessor<E> implements Wrapper<E> {
	public final Field field;
	public final Supplier<Object> object;
	public Accessor(Supplier<Object> object, Field field) {
		this.object = object;
		this.field = field;
	}
	
	@Override
	public void setValue(E value) {
		try {
			field.set(object.get(), value);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getValue() {
		try {
			return (E) field.get(object.get());
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

}
