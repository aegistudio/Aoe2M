package net.aegistudio.aoe2m;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class VariantList<T> {
	public final List<T> element = new ArrayList<>();
	
	public final Supplier<T> factory; 
	public final ListTranslation<T> translation;
	
	public static interface ListTranslation<T> {
		public void translate(T t, Translator translator) 
				throws IOException, CorruptionException;
	}
	public VariantList(Supplier<T> factory, ListTranslation<T> translation) {
		this.factory = factory;
		this.translation = translation;
	}
	
	@SuppressWarnings("unchecked")
	public void build(Translator translator) throws IOException, CorruptionException {
		Wrapper<Integer> count = new Container<Integer>(element.size());
		translator.signed32(count);
		
		translator.array(count.getValue(), element, factory, 
				element -> translation.translate(element, translator));
	}
	
	public int size() {
		return element.size();
	}
	
	public T get(int index) {
		return element.get(index);
	}
}
