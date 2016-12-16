package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.aegistudio.aoe2m.scx.Wrapper;
import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.FieldTranslator;

public class OrderedList<T> {
	public final List<T> element = new ArrayList<>();
	public final List<Wrapper<Integer>> order = new ArrayList<>();
	
	public final Supplier<T> factory; 
	public final OrderedListTranslation<T> translation;
	
	public static interface OrderedListTranslation<T> {
		public void translate(T t, FieldTranslator translator) 
				throws IOException, CorruptionException;
	}
	public OrderedList(Supplier<T> factory, OrderedListTranslation<T> translation) {
		this.factory = factory;
		this.translation = translation;
	}
	
	public void build(FieldTranslator translator) throws IOException, CorruptionException {
		Wrapper<Integer> conditionCount = new Wrapper<Integer>(element.size());
		translator.signed32(conditionCount);
		translator.array(conditionCount.getValue(), element, factory, 
				element -> translation.translate(element, translator));
		translator.array(conditionCount.getValue(), order, 
				() -> new Wrapper<Integer>(0), 
				order -> translator.signed32(order));
	}
	
	public int size() {
		return element.size();
	}
	
	public T get(int index) {
		return element.get(index);
	}
}
