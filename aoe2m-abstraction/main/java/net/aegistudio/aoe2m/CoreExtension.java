package net.aegistudio.aoe2m;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * CoreExtension that extends the core
 * feature of aoe2m.
 * 
 * @author aegistudio
 */

public interface CoreExtension {
	/**
	 * Initialize extension. You should
	 * record your extension here.
	 */
	public void init(CoreModel core);
	
	/**
	 * Load extension data from the model.
	 */
	public void load(InputStream data) 
			throws IOException, Aoe2mException;
	
	/**
	 * Save extension data to the model.
	 */
	public void save(OutputStream data)
			throws IOException, Aoe2mException;
}
