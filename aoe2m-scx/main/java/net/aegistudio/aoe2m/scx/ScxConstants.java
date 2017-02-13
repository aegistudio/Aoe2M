package net.aegistudio.aoe2m.scx;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;

/**
 * Defines usually used constants (like division) in a 
 * scenario file.
 * 
 * @author aegistudio
 */

public class ScxConstants {
	public static void unused(FieldTranslator translator) 
			throws IOException, CorruptionException {
		translator.constUnsigned32(0l);
	}
	
	public static void division(FieldTranslator translator) 
			throws IOException, CorruptionException {
		translator.constUnsigned32(0x0ffffff9dl);
	}
}
