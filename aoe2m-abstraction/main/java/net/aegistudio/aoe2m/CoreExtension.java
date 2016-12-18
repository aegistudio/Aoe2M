package net.aegistudio.aoe2m;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * CoreExtension that extends the core
 * feature of aoe2m.
 * 
 * Please notice there will be invocation
 * when the application being initializing
 * and deposing With document == null.
 * 
 * @author aegistudio
 */

public interface CoreExtension {
	/**
	 * Initialize extension. You should
	 * record your extension here.
	 */
	public void init(Document document, CoreModel core);
	
	/**
	 * Load extension data from the model.
	 */
	public void load(Document document, InputStream data) 
			throws IOException, Aoe2mException;
	
	/**
	 * Save extension data to the model.
	 */
	public void save(Document document, OutputStream data)
			throws IOException, Aoe2mException;
}
