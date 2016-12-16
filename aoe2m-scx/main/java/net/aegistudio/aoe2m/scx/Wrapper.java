package net.aegistudio.aoe2m.scx;

public class Wrapper<T> {
	T value;
	
	public Wrapper(T initValue) {
		this.value = initValue;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	public String toString() {
		return value.toString();
	}
}
