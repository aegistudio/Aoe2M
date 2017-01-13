package net.aegistudio.aoe2m;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

public class EnumWrapper<T extends Enum<T>> extends Wrapper<T> {
	public EnumWrapper(T initValue) {
		super(initValue);
	}

	public int intValue() {
		return super.value.ordinal();
	}
	
	@SuppressWarnings("unchecked")
	public void setOrdinal(int value) {
		try {
			Method method = super.value.getClass().getMethod("values");
			super.value = (T) Array.get(method.invoke(null), value);
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
	}
}
