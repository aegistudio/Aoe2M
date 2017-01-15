package net.aegistudio.aoe2m.ra;

import java.io.IOException;
import java.io.OutputStream;

public class AccessOutputStream extends OutputStream {
	protected final RandomAccessible output;
	public AccessOutputStream(RandomAccessible output) {
		this.output = output;
	}
	
	@Override
	public void write(int arg0) throws IOException {
		output.write(arg0);
	}
}
