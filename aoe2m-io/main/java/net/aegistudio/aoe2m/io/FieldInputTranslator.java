package net.aegistudio.aoe2m.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.EnumWrapper;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.Text;

public class FieldInputTranslator implements FieldTranslator {
	private final String charset;
	private final FieldInputStream fieldInputStream;
	public FieldInputTranslator(InputStream fieldInputStream, String charset) {
		this.charset = charset;
		this.fieldInputStream = new FieldInputStream(fieldInputStream, charset);
	}
	
	@Override
	public void unsigned16(Wrapper<Integer> field) throws IOException {
		field.setValue(fieldInputStream.readUnsigned16());
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
	public <T> void array(int length, List<T> list, Supplier<T> factory, 
			@SuppressWarnings("unchecked") ArrayTranslation<T>... translations)
			throws IOException, CorruptionException {
		if(list != null) list.clear();
		for(int i = 0; i < length; i ++) 
			list.add(factory.get());
		
		for(ArrayTranslation<T> translation : translations)
			for(int i = 0; i < length; i ++) {
				T value = list.get(i);
				translation.translate(value);
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

	public static final int REALLOC_SIZE = 4096;
	
	@SuppressWarnings("resource")
	@Override
	public void bitmap(Wrapper<BufferedImage> image) throws IOException, CorruptionException {
		// Retrieve essential info from info header.
		byte[] BITMAPINFOHDR = new byte[40];
		if(fieldInputStream.read(BITMAPINFOHDR) != 40) throw new EOFException();
		FieldInputStream tempInputStream = new FieldInputStream(
				new ByteArrayInputStream(BITMAPINFOHDR), charset);
		
		CorruptionException.assertInt(40, tempInputStream.readSigned32());
		tempInputStream.skip(4 + 4 + 2 + 2 + 4);
		int size = tempInputStream.readSigned32();
		tempInputStream.skip(4 + 4);
		int paletteSize = tempInputStream.readSigned32() * 4;
		
		// Buffer reallocation.
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		FieldOutputStream tempFieldOutput = new FieldOutputStream(output);
		
		// BITMAPCOREHDR
		tempFieldOutput.write('B'); 
		tempFieldOutput.write('M'); 
		tempFieldOutput.write32(14 + 40 + size + paletteSize);
		tempFieldOutput.write32(0);
		tempFieldOutput.write32(14 + 40 + paletteSize);
		
		//BITMAPINFOHDR
		tempFieldOutput.write(BITMAPINFOHDR);
		
		//BITMAPBODY
		int remained = size + paletteSize;
		byte[] realloc = new byte[REALLOC_SIZE];
		
		while(remained > 0) {
			int readSize = fieldInputStream.read(
					realloc, 0, Math.min(remained, REALLOC_SIZE));
			
			tempFieldOutput.write(realloc, 0, readSize);
			tempFieldOutput.flush();
			remained = remained - readSize;
		}

		// Finish
		byte[] result = output.toByteArray();
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(result));
		if(bufferedImage == null) throw new CorruptionException("Bitmap", "Unknown");
		
		image.setValue(bufferedImage);
	}

	@Override
	public void constString(String field) throws CorruptionException, IOException {
		String newString = fieldInputStream.readConstLengthString(field.length());
		if(!newString.equals(field)) throw new CorruptionException(field, newString);
	}

	@Override
	public void constSigned32(int field) throws CorruptionException, IOException {
		CorruptionException.assertInt(field, fieldInputStream.readSigned32());
	}
}
