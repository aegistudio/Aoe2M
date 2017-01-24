package net.aegistudio.aoe2m.media;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Storage {
	public Storage chdir(String path) throws IOException;
	
	public Storage create(String path) throws IOException;
	
	public String name();
	
	public InputStream read() throws IOException;
	
	public OutputStream write() throws IOException;
	
	public Storage[] list() throws IOException;
	
	public void erase() throws IOException;
}
