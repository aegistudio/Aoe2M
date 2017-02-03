package net.aegistudio.aoe2m;

public class Container<T> implements Wrapper<T> {
	protected T value;
	
	public Container(T initValue) {
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
		if(another instanceof Container)
			return equals(((Container)another).getValue());
		return value.equals(another);
	}
	
	/* Some candies for functional interfaces */
	public static Container<Byte> byte0() {
		return new Container<>((byte)0);
	}
	
	public static Container<Short> short0() {
		return new Container<>((short)0);
	}
	
	public static Container<Short> short1m() {
		return new Container<>((short)-1);
	}
	
	public static Container<Integer> int0() {
		return new Container<>(0);
	}
	
	public static Container<Long> long0() {
		return new Container<>(0l);
	}
	
	public static Container<Float> float0() {
		return new Container<>(0f);
	}
	
	public static Container<String> string0() {
		return new Container<>("");
	}
}
