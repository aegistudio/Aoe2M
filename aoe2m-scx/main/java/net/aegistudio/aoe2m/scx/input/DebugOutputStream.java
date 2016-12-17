package net.aegistudio.aoe2m.scx.input;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class DebugOutputStream extends OutputStream implements Debuggable {
	private final OutputStream master;
	private final PrintStream side;
	public DebugOutputStream(OutputStream master, PrintStream side) {
		this.master = master;
		this.side = side;
	}
	
	@Override
	public void write(int value) throws IOException {
		master.write(value);
		String hex = "00" + Integer.toHexString(value);
		hex = hex.substring(hex.length() - 2);
		side.print(hex);
		side.print(" ");
	}

	public void start(String type) {
		side.print(type + "[ ");
	}
	
	public void start() {
		start("");
	}
	
	public void end() {
		side.print("] \n");
	}
}
