package net.aegistudio.aoe2m;

public class Wrapper<T> {
	protected T value;
	
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
	
	public int hashCode() {
		if(value == null) return 0;
		return value.hashCode();
	}
	
	@SuppressWarnings("rawtypes")
	public boolean equals(Object another) {
		if(value == null) return another == null;
		if(another instanceof Wrapper)
			return equals(((Wrapper)another).getValue());
		return value.equals(another);
	}
}
