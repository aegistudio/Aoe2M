package net.aegistudio.aoe2m.scx.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.EnumWrapper;
import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.Text;
import net.aegistudio.aoe2m.scx.Wrapper;

public class FieldInputTranslator implements FieldTranslator {
	private final FieldInputStream fieldInputStream;
	public FieldInputTranslator(InputStream fieldInputStream, String charset) {
		this.fieldInputStream = new FieldInputStream(fieldInputStream, charset);
	}
	
	@Override
	public void unsigned32(Wrapper<Long> field) throws IOException {
		field.setValue(fieldInputStream.readUnsigned32());
	}

	@Override
	public void signed32(Wrapper<Integer> field) throws IOException {
		field.setValue(fieldInputStream.readSigned32());
	}

	@Override
	public void string32(Wrapper<Text> field) throws IOException {
		field.setValue(fieldInputStream.readString32());
	}

	@Override
	public void string16(Wrapper<Text> field) throws IOException {
		field.setValue(fieldInputStream.readString16());
	}

	@Override
	public void division() throws CorruptionException, IOException {
		CorruptionException.assertDivision(fieldInputStream.readUnsigned32());
	}

	@Override
	public void unused() throws CorruptionException, IOException {
		CorruptionException.assertUnused(fieldInputStream.readUnsigned32());
	}

	@Override
	public void constUnsigned32(long field) throws CorruptionException, IOException {
		CorruptionException.assertLong(field, fieldInputStream.readUnsigned32());
	}

	@Override
	public void constByte(int field) throws CorruptionException, IOException {
		CorruptionException.assertInt(field, fieldInputStream.read());
	}

	@Override
	public void skip(long length) throws IOException {
		fieldInputStream.skip(length);
	}
	
	@Override
	public void constString(int length, Wrapper<String> string) throws IOException {
		string.setValue(fieldInputStream.readConstLengthString(length));
	}

	@Override
	public void bool32(Wrapper<Boolean> field) throws IOException {
		field.setValue(fieldInputStream.readUnsigned32() != 0);
	}

	@Override
	public <T extends Enum<T>> void enum32(EnumWrapper<T> field) throws IOException {
		field.setOrdinal((int) fieldInputStream.readUnsigned32());
	}
	
	@Override
	public <T extends Enum<T>> void enum8(EnumWrapper<T> field) throws IOException {
		field.setOrdinal((int) fieldInputStream.read());
	}

	@Override
	public void signed16(Wrapper<Short> field) throws IOException {
		field.setValue(fieldInputStream.readSigned16());
	}

	@Override
	public void float32(Wrapper<Float> field) throws IOException {
		field.setValue(fieldInputStream.readFloat32());
	}
	
	@Override
	public void constFloat(float expected) throws IOException, CorruptionException {
		CorruptionException.assertFloat(expected, fieldInputStream.readFloat32());
	}

	@Override
	public void signed8(Wrapper<Byte> field) throws IOException {
		field.setValue((byte) fieldInputStream.read());
	}

	@Override
	public void constUnsigned16(int field) throws CorruptionException, IOException {
		CorruptionException.assertInt(field, fieldInputStream.readUnsigned16());
	}

	@Override
	public <T> void array(int length, List<T> list, Supplier<T> factory, ArrayTranslation<T> translation)
			throws IOException, CorruptionException {
		if(list != null) list.clear();
		for(int i = 0; i < length; i ++) {
			T value = factory.get();
			translation.translate(value);
			list.add(value);
		}
	}
	
	@Override
	public void end() throws CorruptionException, IOException {
		try {
			fieldInputStream.read();
			throw new CorruptionException("StreamOpening", "EndOfStream");
		}
		catch(EOFException e) {
			
		}
	}
}
