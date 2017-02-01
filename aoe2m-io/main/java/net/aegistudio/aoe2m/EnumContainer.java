package net.aegistudio.aoe2m;

public class EnumContainer<T extends Enum<T>> extends Container<T>{
	protected final EnumWrapper<T> enumWrapper;
	@SuppressWarnings("unchecked")
	public EnumContainer(T initValue) {
		super(initValue);
		this.enumWrapper = new EnumWrapper<T>((Class<T>) initValue.getClass(), this);
	}
	
	public EnumWrapper<T> enumWrapper() {
		return enumWrapper;
	}
}
