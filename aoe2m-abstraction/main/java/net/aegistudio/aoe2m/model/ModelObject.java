package net.aegistudio.aoe2m.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.impcall.ValueObserver;

/**
 * Every model object is mapped to one
 * or more scenario persistent segment(s).
 * 
 * You could replace the ordinary model object
 * with a2m specified one. (Which is called
 * pseudo object).
 * 
 * This conversion is mono-directional and
 * can't be reverted. Though basic model
 * will offer de-marshaling one.
 * 
 * @author aegistudio
 */

public interface ModelObject<E> {
	public ValueObserver<E> self();
	
	public void read(InputStream inputStream) throws IOException, Aoe2mException;
	
	public void write(OutputStream outputStream) throws IOException, Aoe2mException;
}
