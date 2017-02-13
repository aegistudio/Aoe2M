package net.aegistudio.aoe2m;

import java.io.IOException;
import java.util.Arrays;

public final class TranslateWrapper {
	public interface ReverseArrayTranslation<T> {
		public void translate(T obj, Translator translator)
			throws IOException, CorruptionException;
	}
	
	public static <T> Translator.ArrayTranslation<T> wrap(
			Translator translator, ReverseArrayTranslation<T> consumer) {
		return obj -> consumer.translate(obj, translator);	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Translator.ArrayTranslation<T>[] wrapAll(
			Translator translator, ReverseArrayTranslation<T>... consumers) {
		return Arrays.stream(consumers)
				.map(consumer -> wrap(translator, consumer))
				.toArray(Translator.ArrayTranslation[]::new);
	}
}
