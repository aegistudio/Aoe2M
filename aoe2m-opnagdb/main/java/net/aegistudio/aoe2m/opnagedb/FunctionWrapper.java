package net.aegistudio.aoe2m.opnagedb;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

import net.aegistudio.aoe2m.opnagedb.fp.FunctionExcept;

public class FunctionWrapper {
	public static <S, T> Function<S, T> mapIgnoreExcept(FunctionExcept<S, T> function) {
		return s -> { try {
				return function.apply(s);
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
