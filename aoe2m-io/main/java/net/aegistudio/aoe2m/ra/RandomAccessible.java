package net.aegistudio.aoe2m.ra;

import java.io.IOException;

/**
 * A disk or memory device that is configured
 * to be capable of random accessing.
 * 
 * @author aegistudio
 */

public interface RandomAccessible {
	public void seek(long offset) throws IOException;
	
	public long length() throws IOException;
	
	public long current() throws IOException;
	
	public void close() throws IOException;
	
	public int read() throws IOException;
	
	public void write(int value) throws IOException;
}
