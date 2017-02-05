package net.aegistudio.aoe2m.pal;

import java.io.IOException;
import java.io.InputStream;

public class WindowsLineReader extends InputStream {
	protected final InputStream input;
	public WindowsLineReader(InputStream input) {
		this.input = input;
	}
	
	@Override
	public int read() throws IOException {
		return input.read();
	}

	private static final int WAIT_FOR_CARRIER = 0;
	private static final int WAIT_FOR_NEWLINE = 1;
	
	public String readLine() throws IOException {
		StringBuilder inputBuffer = new StringBuilder();
		int state = WAIT_FOR_CARRIER;
		
		while(true) {
			int c = input.read();
			if(c < 0) return null;
			
			if(c == '\r') 
				if(state == WAIT_FOR_CARRIER) {
					state = WAIT_FOR_NEWLINE;	continue;
				} 
				else if(state == WAIT_FOR_NEWLINE) {
					inputBuffer.append('\r');	continue;
				}
			
			if(c == '\n' && state == WAIT_FOR_NEWLINE) 
				break;
			
			state = WAIT_FOR_CARRIER;
			inputBuffer.append((char)c);
		}
		return new String(inputBuffer);
	}
}
