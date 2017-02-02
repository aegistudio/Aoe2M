package net.aegistudio.aoe2m;

import java.io.IOException;
import java.util.Arrays;

public final class TranslateWrapper {
	public interface ReverseArrayTranslation<T> {
		public void translate(T obj, FieldTranslator translator)
			throws IOException, CorruptionException;
	}
	
	public static <T> FieldTranslator.ArrayTranslation<T> wrap(
			FieldTranslator translator, ReverseArrayTranslation<T> consumer) {
		return obj -> consumer.translate(obj, translator);	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> FieldTranslator.ArrayTranslation<T>[] wrapAll(
			FieldTranslator translator, ReverseArrayTranslation<T>... consumers) {
		return Arrays.stream(consumers)
				.map(consumer -> wrap(translator, consumer))
				.toArray(FieldTranslator.ArrayTranslation[]::new);
	}
}
