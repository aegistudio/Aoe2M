package net.aegistudio.aoe2m.empires2x1p1;

import java.io.IOException;

import net.aegistudio.aoe2m.CastDelegate;
import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class Resource {
	public final Wrapper<Short> type = Container.short0();
	
	public final Wrapper<Float> amount = Container.float0();
	protected final Wrapper<Short> castShortAmount = new CastDelegate<Float, Short>(
			amount, out -> (short)(float)out, in -> (float)(short)in);
	
	public final Wrapper<Short> used = Container.short0();
	protected final Wrapper<Byte> castByteUsed = new CastDelegate<Short, Byte>(
			used, out -> (byte)(short)out, in -> (short)(byte)in);
	
	public void translateStorage(FieldTranslator translator) throws IOException {
		translator.signed16(type);
		translator.float32(amount);
		translator.signed8(castByteUsed);
	}
	
	public void translateCost(FieldTranslator translator) throws IOException {
		translator.signed16(type);
		translator.signed16(castShortAmount);
		translator.signed16(used);
	}
}
