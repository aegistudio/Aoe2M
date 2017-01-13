package net.aegistudio.aoe2m.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class DebugInputStream extends InputStream implements Debuggable {
	private final InputStream input;
	private final PrintStream output;
	public DebugInputStream(InputStream input, PrintStream output) {
		this.input = input;
		this.output = output;
	}
	
	@Override
	public int read() throws IOException {
		int read = input.read();
		if(read >= 0) {
			String hex = "00" + Integer.toHexString(read);
			hex = hex.substring(hex.length() - 2);
			output.print(hex);
			output.print(" ");
		}
		return read;
	}

	public void start(String type) {
		output.print(type + "[ ");
	}
	
	public void start() {
		start("");
	}
	
	public void end() {
		output.print("] \n");
	}
}
