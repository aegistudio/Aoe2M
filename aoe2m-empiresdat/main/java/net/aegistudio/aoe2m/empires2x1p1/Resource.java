package net.aegistudio.aoe2m.empires2x1p1;

import java.io.IOException;

import net.aegistudio.aoe2m.Transform;
import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class Resource {
	public final Wrapper<Short> type = Container.short1m();
	
	public final Wrapper<Float> amount = Container.float0();
	protected final Wrapper<Short> castShortAmount = new Transform<Float, Short>(
			amount, out -> (short)(float)out, in -> (float)(short)in);
	
	public final Wrapper<Short> used = Container.short0();
	protected final Wrapper<Byte> castByteUsed = new Transform<Short, Byte>(
			used, out -> (byte)(short)out, in -> (short)(byte)in);
	
	public void translateStorage(Translator translator) throws IOException {
		translator.signed16(type);
		translator.float32(amount);
		translator.signed8(castByteUsed);
	}
	
	public void translateCost(Translator translator) throws IOException {
		translator.signed16(type);
		translator.signed16(castShortAmount);
		translator.signed16(used);
	}
	
	public void translateResearch(Translator translator) throws IOException {
		translator.signed16(type);
		translator.signed16(castShortAmount);
		translator.signed8(castByteUsed);
	}
}
