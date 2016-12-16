package net.aegistudio.aoe2m.scx;

@SuppressWarnings("serial")
public class CorruptionException extends Exception {
	public <T> CorruptionException(T original, T expected) {
		super("Corrupted value " + expected + " where " + original + " should be presented.");
	}
	
	public static void assertLong(long original, long expected) throws CorruptionException {
		if(original != expected) throw new CorruptionException(original, expected);
	}
	
	public static void assertInt(int original, int expected) throws CorruptionException {
		if(original != expected) throw new CorruptionException(original, expected);
	}
	
	public static void assertFloat(float original, float expected) throws CorruptionException {
		if(original != expected) throw new CorruptionException(original, expected);
	}
	
	public static void assertDouble(double original, double expected) throws CorruptionException {
		if(original != expected) throw new CorruptionException(original, expected);
	}
	
	public static void assertDivision(long expected) throws CorruptionException {
		assertLong(0x0ffffff9dl, expected);
	}
	
	public static void assertUnused(long expected) throws CorruptionException {
		assertLong(0, expected);
	}
}
