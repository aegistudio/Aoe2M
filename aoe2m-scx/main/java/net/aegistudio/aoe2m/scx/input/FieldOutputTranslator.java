package net.aegistudio.aoe2m.scx.input;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.EnumWrapper;
import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.Text;
import net.aegistudio.aoe2m.scx.Wrapper;

public class FieldOutputTranslator implements FieldTranslator {
	private final FieldOutputStream fieldOutputStream;
	public FieldOutputTranslator(OutputStream fieldOutputStream) {
		this.fieldOutputStream = new FieldOutputStream(fieldOutputStream);
	}
	
	@Override
	public void constString(int length, Wrapper<String> string) throws IOException {
		fieldOutputStream.writeConstString(length, string.getValue());
	}

	@Override
	public void unsigned32(Wrapper<Long> field) throws IOException {
		fieldOutputStream.write32((int)(long)field.getValue());
	}

	@Override
	public <T extends Enum<T>> void enum32(EnumWrapper<T> field) throws IOException {
		fieldOutputStream.write32(field.intValue());
	}

	@Override
	public <T extends Enum<T>> void enum8(EnumWrapper<T> field) throws IOException {
		fieldOutputStream.write(field.intValue());
	}

	@Override
	public void float32(Wrapper<Float> field) throws IOException {
		fieldOutputStream.writeFloat32(field.getValue());
	}

	@Override
	public void signed32(Wrapper<Integer> field) throws IOException {
		fieldOutputStream.write32(field.getValue());
	}

	@Override
	public void signed16(Wrapper<Short> field) throws IOException {
		fieldOutputStream.write16(field.getValue());
	}

	@Override
	public void signed8(Wrapper<Byte> field) throws IOException {
		fieldOutputStream.write(field.getValue());
	}

	@Override
	public void string32(Wrapper<Text> field) throws IOException {
		fieldOutputStream.writeString32(field.getValue());
	}

	@Override
	public void string16(Wrapper<Text> field) throws IOException {
		fieldOutputStream.writeString16(field.getValue());
	}

	@Override
	public void bool32(Wrapper<Boolean> field) throws IOException {
		fieldOutputStream.write32(field.getValue()? 1 : 0);
	}

	@Override
	public void division() throws CorruptionException, IOException {
		fieldOutputStream.write32((int) 0x0ffffff9dl);
	}

	@Override
	public void unused() throws CorruptionException, IOException {
		fieldOutputStream.write32(0);
	}

	@Override
	public void constUnsigned16(int field) throws CorruptionException, IOException {
		fieldOutputStream.write16((short) field);
	}

	@Override
	public void constUnsigned32(long field) throws CorruptionException, IOException {
		fieldOutputStream.write32((int) field);
	}

	@Override
	public void constByte(int field) throws CorruptionException, IOException {
		fieldOutputStream.write(field);
	}

	@Override
	public void skip(long length) throws IOException {
		fieldOutputStream.skip(length);
	}

	@Override
	public void constFloat(float expected) throws IOException, CorruptionException {
		fieldOutputStream.writeFloat32(expected);
	}

	@Override
	public <T> void array(int length, List<T> list, Supplier<T> factory, ArrayTranslation<T> translation)
			throws IOException, CorruptionException {
		for(int i = 0; i < list.size(); i ++) {
			T value = list.get(i);
			translation.translate(value);
		}
	}

	@Override
	public void end() throws IOException, CorruptionException {
		fieldOutputStream.close();
	}

	@Override
	public void bitmap(Wrapper<BufferedImage> image) throws IOException, CorruptionException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(image.getValue(), "bmp", buffer);
		byte[] full = buffer.toByteArray();
		byte[] trim = new byte[full.length - 14];
		System.arraycopy(full, 14, trim, 0, trim.length);
		fieldOutputStream.write(trim);
	}
}
