package net.aegistudio.aoe2m.io;

import java.io.IOException;
import java.io.OutputStream;

import net.aegistudio.aoe2m.Text;

public class FieldOutputStream extends OutputStream {
	private OutputStream outputStream;
	private byte[] bytes = new byte[8];
	public FieldOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void close() throws IOException {
		this.outputStream.close();
	}
	
	private void writeBytes(long max, int length) throws IOException {
		for(int i = 0; i < 8; i ++) {
			bytes[i] = (byte) (max & 0x0ff);
			max = max >> 8;
		}
		write(bytes, 0, length);
	}
	
	@Override
	public void write(int arg0) throws IOException {
		if(arg0 < 0) arg0 += 256;
		outputStream.write(arg0);
	}
	
	public void write16(short value) throws IOException {
		writeBytes(value, 2);
	}
	
	public void write32(int value) throws IOException {
		writeBytes(value, 4);
	}
	
	public void writeFloat32(float value) throws IOException {
		write32(Float.floatToRawIntBits(value));
	}
	
	public void write64(long value) throws IOException {
		writeBytes(value, 8);
	}
	
	public void skip(long length) throws IOException {
		for(long i = 0; i < length; i ++) write(0);
	}
	
	public void writeConstString(int length, String value) throws IOException {
		byte[] sourceBytes = value.getBytes();
		int written = Math.min(length, sourceBytes.length);
		write(sourceBytes, 0, written);
		if(written < length) skip(length - written);
	}
	
	public void writeString16(Text text) throws IOException {
		write16((short) text.length);
		writeConstString((int) text.length, text.string);
	}
	
	public void writeString32(Text text) throws IOException {
		write32((int) text.length);
		writeConstString((int) text.length, text.string);
	}
}
