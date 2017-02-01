package net.aegistudio.aoe2m;

public interface Wrapper<T> {
	public void setValue(T value);
	
	public T getValue();
}
