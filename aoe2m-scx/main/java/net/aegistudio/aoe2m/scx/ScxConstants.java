package net.aegistudio.aoe2m.scx;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;

/**
 * Defines usually used constants (like division) in a 
 * scenario file.
 * 
 * @author aegistudio
 */

public class ScxConstants {
	public static void unused(Translator translator) 
			throws IOException, CorruptionException {
		translator.constInteger(0);
	}
	
	public static void division(Translator translator) 
			throws IOException, CorruptionException {
		translator.constInteger((int)0x0ffffff9dl);
	}
}
