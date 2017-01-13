package net.aegistudio.aoe2m.io;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.EnumWrapper;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.Text;

public class DebugInputTranslator extends FieldInputTranslator {
	private DebugInputStream debug;
	
	public DebugInputTranslator(DebugInputStream debug, String charset) {
		super(debug, charset);
		this.debug = debug;
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
		debug("u32", () -> super.unsigned32(field));
	}

	@Override
	public void signed32(Wrapper<Integer> field) throws IOException {
		debug("s32", () -> super.signed32(field));
	}

	@Override
	public void string32(Wrapper<Text> field) throws IOException {
		debug("str32", () -> super.string32(field));
	}

	@Override
	public void string16(Wrapper<Text> field) throws IOException {
		debug("str16", () -> super.string16(field));
	}

	@Override
	public void division() throws CorruptionException, IOException {
		constDebug("division", () -> super.division());
	}

	@Override
	public void unused() throws CorruptionException, IOException {
		constDebug("unused", () -> super.unused());
	}

	@Override
	public void constUnsigned32(long field) throws CorruptionException, IOException {
		constDebug("cu32", () -> super.constUnsigned32(field));
	}

	@Override
	public void constByte(int field) throws CorruptionException, IOException {
		constDebug("cu8", () -> super.constByte(field));
	}

	@Override
	public void skip(long length) throws IOException {
		debug("skip" + length, () -> super.skip(length));
	}

	@Override
	public void constString(int length, Wrapper<String> string) throws IOException {
		debug("cstr" + length, () -> super.constString(length, string));
	}

	@Override
	public void bool32(Wrapper<Boolean> field) throws IOException {
		debug("bool32", () -> super.bool32(field));
	}

	@Override
	public <T extends Enum<T>> void enum32(EnumWrapper<T> field) throws IOException {
		debug("enum32", () -> super.enum32(field));
	}
	
	@Override
	public <T extends Enum<T>> void enum8(EnumWrapper<T> field) throws IOException {
		debug("enum8", () -> super.enum8(field));
	}

	@Override
	public void signed16(Wrapper<Short> field) throws IOException {
		debug("s16", () -> super.signed16(field));
	}

	@Override
	public void float32(Wrapper<Float> field) throws IOException {
		debug("f32", () -> super.float32(field));
	}
	
	@Override
	public void constFloat(float expected) throws IOException, CorruptionException {
		constDebug("cf32", () -> super.constFloat(expected));
	}

	@Override
	public void signed8(Wrapper<Byte> field) throws IOException {
		debug("s8", () -> super.signed8(field));
	}

	@Override
	public void constUnsigned16(int field) throws CorruptionException, IOException {
		constDebug("cu16", () -> super.constUnsigned16(field));
	}

}
