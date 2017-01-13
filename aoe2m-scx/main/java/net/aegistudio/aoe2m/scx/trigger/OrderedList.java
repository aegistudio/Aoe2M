package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.VariantList;
import net.aegistudio.aoe2m.Wrapper;

public class OrderedList<T> extends VariantList<T> {
	public final List<Wrapper<Integer>> order = new ArrayList<>();
	
	public OrderedList(Supplier<T> factory, ListTranslation<T> translation) {
		super(factory, translation);
	}
	
	public void build(FieldTranslator translator) throws IOException, CorruptionException {
		super.build(translator);
		translator.array(element.size(), order, 
				() -> new Wrapper<Integer>(0), 
				order -> translator.signed32(order));
	}
}
