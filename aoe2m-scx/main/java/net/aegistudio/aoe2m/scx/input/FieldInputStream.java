package net.aegistudio.aoe2m.scx.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import net.aegistudio.aoe2m.scx.String16;
import net.aegistudio.aoe2m.scx.String32;

public class FieldInputStream extends InputStream {
	private final InputStream inputStream;
	private final String charset;
	protected byte[] bytes = new byte[8];
	
	public FieldInputStream(InputStream inputStream, String charset) {
		this.inputStream = inputStream;
		this.charset = charset;
	}
	
	@Override
	public int read() throws IOException {
		int value = this.inputStream.read();
		if(value < 0) throw new EOFException();
		return value;
	}
	
	public String readConstLengthString(int length) throws IOException {
		byte[] string = new byte[length];
		this.inputStream.read(string);
		return new String(string, charset);
	}
	
	public String16 readString16() throws IOException {
		int length = this.readUnsigned16();
		String string = this.readConstLengthString(length);
		return new String16(length, string);
	}
	
	public String32 readString32() throws IOException {
		long length = this.readUnsigned32();
		String string = this.readConstLengthString((int) length);
		return new String32(length, string);
	}
	
	public int readUnsigned16() throws IOException {
		return (short) readBytes(2, false);
	}
	
	public short readSigned16() throws IOException {
		return (short) readBytes(2, true);
	}
	
	public long readUnsigned32() throws IOException {
		return readBytes(4, false);
	}

	public int readSigned32() throws IOException {
		return (int) readBytes(4, false);
	}
	
	public float readFloat32() throws IOException {
		return Float.intBitsToFloat(this.readSigned32());
	}
	
	public long readBytes(int length, boolean sext) throws IOException {
		inputStream.read(bytes, 0, length);
		long value = 0;
		for(int i = 0; i < length; i ++)
			value += sext? makeFieldSext(i) : makeField(i);
		return value;	
	}
	
	protected long makeField(int i) {
		return ((long)bytes[i] & 0x0ffl) << (i * 8);
	}
	
	protected long makeFieldSext(int i) {
		return bytes[i] << (i * 8);
	}
}
