package net.aegistudio.aoe2m;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

public class EnumWrapper<T extends Enum<T>> extends Delegate<T> {
	protected Method values;
	public EnumWrapper(Class<T> enumClass, Wrapper<T> initValue) {
		super(initValue);
		try {
			values = enumClass.getMethod("values");
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	public int intValue() {
		return super.getValue().ordinal();
	}
	
	@SuppressWarnings("unchecked")
	public void setOrdinal(int value) {
		try {
			super.setValue((T) Array.get(values.invoke(null), value));
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
	}
}
