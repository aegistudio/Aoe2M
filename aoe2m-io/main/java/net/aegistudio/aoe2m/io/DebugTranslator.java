package net.aegistudio.aoe2m.io;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.EnumWrapper;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.Text;

public class DebugTranslator implements FieldTranslator {
	private Debuggable debug;
	private FieldTranslator wrapped;
	
	public DebugTranslator(Debuggable debug, FieldTranslator wrapped) {
		this.debug = debug;
		this.wrapped = wrapped;
	}

	protected interface IOECallable { public void call() throws IOException;}
	protected void debug(String type, IOECallable next) throws IOException {
		debug.start(type);
		next.call();
		debug.end();
	}
	protected interface IOECCallable { public void call() throws IOException, CorruptionException; }
	protected void constDebug(String type, IOECCallable next) throws IOException, CorruptionException {
		debug.start(type);
		next.call();
		debug.end();
	}
	
	@Override
	public void unsigned32(Wrapper<Long> field) throws IOException {
		debug("u32", () -> wrapped.unsigned32(field));
	}

	@Override
	public void signed32(Wrapper<Integer> field) throws IOException {
		debug("s32", () -> wrapped.signed32(field));
	}

	@Override
	public void string32(Wrapper<Text> field) throws IOException {
		debug("str32", () -> wrapped.string32(field));
	}

	@Override
	public void string16(Wrapper<Text> field) throws IOException {
		debug("str16", () -> wrapped.string16(field));
	}

	@Override
	public void division() throws CorruptionException, IOException {
		constDebug("division", () -> wrapped.division());
	}

	@Override
	public void unused() throws CorruptionException, IOException {
		constDebug("unused", () -> wrapped.unused());
	}

	@Override
	public void constUnsigned32(long field) throws CorruptionException, IOException {
		constDebug("cu32", () -> wrapped.constUnsigned32(field));
	}

	@Override
	public void constByte(int field) throws CorruptionException, IOException {
		constDebug("cu8", () -> wrapped.constByte(field));
	}

	@Override
	public void skip(long length) throws IOException {
		debug("skip" + length, () -> wrapped.skip(length));
	}

	@Override
	public void constString(int length, Wrapper<String> string) throws IOException {
		debug("cstr" + length, () -> wrapped.constString(length, string));
	}

	@Override
	public void bool32(Wrapper<Boolean> field) throws IOException {
		debug("bool32", () -> wrapped.bool32(field));
	}

	@Override
	public <T extends Enum<T>> void enum32(EnumWrapper<T> field) throws IOException {
		debug("enum32", () -> wrapped.enum32(field));
	}
	
	@Override
	public <T extends Enum<T>> void enum8(EnumWrapper<T> field) throws IOException {
		debug("enum8", () -> wrapped.enum8(field));
	}

	@Override
	public void signed16(Wrapper<Short> field) throws IOException {
		debug("s16", () -> wrapped.signed16(field));
	}

	@Override
	public void float32(Wrapper<Float> field) throws IOException {
		debug("f32", () -> wrapped.float32(field));
	}
	
	@Override
	public void constFloat(float expected) throws IOException, CorruptionException {
		constDebug("cf32", () -> wrapped.constFloat(expected));
	}

	@Override
	public void signed8(Wrapper<Byte> field) throws IOException {
		debug("s8", () -> wrapped.signed8(field));
	}

	@Override
	public void constUnsigned16(int field) throws CorruptionException, IOException {
		constDebug("cu16", () -> wrapped.constUnsigned16(field));
	}
	
	public void end() throws IOException, CorruptionException {
		wrapped.end();
	}

	@Override
	public <T> void array(int length, List<T> list, Supplier<T> factory, 
			@SuppressWarnings("unchecked") ArrayTranslation<T>... translation)
			throws IOException, CorruptionException {
		wrapped.array(length, list, factory, translation);
	}

	@Override
	public void bitmap(Wrapper<BufferedImage> image) throws IOException, CorruptionException {
		wrapped.bitmap(image);
	}

	@Override
	public void constString(String field) throws CorruptionException, IOException {
		wrapped.constString(field);
	}

	@Override
	public void unsigned16(Wrapper<Integer> field) throws IOException {
		debug("u16", () -> wrapped.unsigned16(field));
	}
}
