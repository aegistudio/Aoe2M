package net.aegistudio.aoe2m.ra;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomFileAdapter implements RandomAccessible {
	protected final RandomAccessFile randomFile;
	public RandomFileAdapter(RandomAccessFile randomFile) {
		this.randomFile = randomFile;
	}
	
	@Override
	public void seek(long offset) throws IOException {
		randomFile.seek(offset);
	}

	@Override
	public long length() throws IOException {
		return randomFile.length();
	}

	@Override
	public long current() throws IOException {
		return randomFile.getFilePointer();
	}

	@Override
	public void close() throws IOException {
		randomFile.close();
	}

	@Override
	public int read() throws IOException {
		return randomFile.read();
	}

	@Override
	public void write(int value) throws IOException {
		randomFile.write(value);
	}
}
