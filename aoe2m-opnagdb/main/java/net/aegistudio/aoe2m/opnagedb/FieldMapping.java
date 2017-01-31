package net.aegistudio.aoe2m.opnagedb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.aegistudio.aoe2m.opnagedb.fp.*;

public class FieldMapping<E> {
	public final Map<String, BiConsumerExcept<E, String>> mapping = new TreeMap<>();
	public final Class<E> type;
	
	public FieldMapping(Class<E> type) {
		this.type = type;
	}
	
	public FieldMapping<E> map(String field, BiConsumerExcept<E, String> consumer) {
		mapping.put(field, consumer);
		return this;
	}
	
	public FieldMapping<E> field(String field, String fieldInternal, Function<String, ?> parser) {
		try {
			Field fieldObject = type.getField(fieldInternal);
			return map(field, (object, string) -> {
				try { fieldObject.set(object, parser.apply(string)); } 
				catch(Exception e) { 
					System.err.println("Error parsing field " + field);
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
	
	public FieldMapping<E> stringField(String field, String fieldInternal) {
		return field(field, fieldInternal, i -> i);
	}
	
	public FieldMapping<E> integerField(String field, String fieldInternal) {
		return field(field, fieldInternal, Integer::parseInt);
	}
	
	public FieldMapping<E> floatField(String field, String fieldInternal) {
		return field(field, fieldInternal, Float::parseFloat);
	}
	
	public FieldMapping<E> booleanField(String field, String fieldInternal, String trueWord) {
		return field(field, fieldInternal, i -> i.equals(trueWord));
	}
	
	public <F extends Enum<?>> FieldMapping<E> enumField(String field, String fieldInternal, Class<F> enumType) {
		try {
			Method valueOf = enumType.getMethod("valueOf", String.class);
			return field(field, fieldInternal, i -> { try {
				return valueOf.invoke(null, i);
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}});
		}
		catch(Exception e) {
			throw new AssertionError(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public FunctionExcept<String[], E> collect(Supplier<E> supplier, String... parameters) {
		BiConsumerExcept<E, String>[] collector = new BiConsumerExcept[parameters.length];
		for(int i = 0; i < parameters.length; i ++) 
			collector[i] = mapping.get(parameters[i]);
		
		return params -> {
			E instance = supplier.get();
			for(int i = 0; i < collector.length; i ++)
				if(collector[i] != null)
					collector[i].apply(instance, params[i]);
			return instance;
		};
	}
	
	public FunctionExcept<String[], E> collect(Consumer<String[]> before, Consumer<String[]> after, 
			Supplier<E> supplier, String... parameters) {
		FunctionExcept<String[], E> internal = collect(supplier, parameters);
		return params -> {
			before.accept(params);
			E instance = internal.apply(params);
			after.accept(params);
			return instance;
		};
	}
}
