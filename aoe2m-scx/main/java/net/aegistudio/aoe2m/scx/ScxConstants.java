package net.aegistudio.aoe2m.scx;

import java.io.IOException;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;

/**
 * Defines usually used constants (like division) in a 
 * scenario file.
 * 
 * @author aegistudio
 */

public class ScxConstants {
	public static void unused(Translator translator) 
			throws IOException, CorruptException {
		translator.constInteger(0);
	}
	
	public static void division(Translator translator) 
			throws IOException, CorruptException {
		translator.constInteger((int)0x0ffffff9dl);
	}
}
