package net.aegistudio.aoe2m.scx.trigger;

import net.aegistudio.aoe2m.scx.Wrapper;

public abstract class OrderedList<T> extends Wrapper<Long> {
	public T[] element;
	public Wrapper<Long>[] order;
	
	public OrderedList() {
		super(0l);
		this.setValue(0l);
	}

	@SuppressWarnings("unchecked")
	public void setValue(Long value) {
		super.setValue(value);
		this.element = list((int)(long)value);
		this.order = new Wrapper[(int)(long)value];

		for(int i = 0; i < value; i ++) {
			this.element[i] = newInstance();
			this.order[i] = new Wrapper<Long>((long) i);
		}
	}
	
	public abstract T[] list(int length);
	
	public abstract T newInstance();
}
