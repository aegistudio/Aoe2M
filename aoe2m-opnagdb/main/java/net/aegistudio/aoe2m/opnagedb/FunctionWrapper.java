package net.aegistudio.aoe2m.opnagedb;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

public class FunctionWrapper {
	public interface ExceptionalFunction<S, T> { public T func(S params) throws Exception; }
	public static <S, T> Function<S, T> mapIgnoreExcept(ExceptionalFunction<S, T> function) {
		return s -> { try {
				return function.func(s);
			}
			catch(Exception e) {
				return null;
			}	};
	}
	
	public static <S, T> Function<S[], T[]> highOrder(
			Function<S, T> function, IntFunction<T[]> generator) {
		return sArray -> Arrays.stream(sArray).map(function).toArray(generator);
	}
}
