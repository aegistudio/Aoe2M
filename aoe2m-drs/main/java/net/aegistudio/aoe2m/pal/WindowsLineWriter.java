package net.aegistudio.aoe2m.pal;

import java.io.IOException;
import java.io.OutputStream;

public class WindowsLineWriter extends OutputStream {
	protected final OutputStream output;
	public WindowsLineWriter(OutputStream output) {
		this.output = output;
	}

	@Override
	public void write(int arg0) throws IOException {
		output.write(arg0);
	}
	
	public void writeLine(String line) throws IOException {
		output.write(line.getBytes());
		output.write('\r');	output.write('\n');
	}
}
