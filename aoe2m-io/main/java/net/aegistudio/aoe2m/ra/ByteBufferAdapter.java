package net.aegistudio.aoe2m.ra;

import java.io.IOException;

public class ByteBufferAdapter implements RandomAccessible {
	protected final byte[] byteBuffer;
	protected int pointer = 0;
	public ByteBufferAdapter(byte[] byteBuffer) {
		this.byteBuffer = byteBuffer;
	}
	
	@Override
	public void seek(long offset) throws IOException {
		pointer = (int) offset;
	}

	@Override
	public long length() throws IOException {
		return byteBuffer.length;
	}

	@Override
	public long current() throws IOException {
		return pointer;
	}

	@Override
	public void close() throws IOException {
		
	}

	@Override
	public int read() throws IOException {
		if(pointer >= byteBuffer.length) return -1;
		else return byteBuffer[pointer ++];
	}

	@Override
	public void write(int value) throws IOException {
		byteBuffer[pointer ++] = (byte) value;
	}
}
