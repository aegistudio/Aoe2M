package net.aegistudio.aoe2m;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

/**
 * As we know, the order we read fields from the file is the same as we write them to file.
 * The field translator takes in the field, and write them to file if needed.
 * 
 * @author aegistudio
 */

public interface FieldTranslator {
	public void constString(int length, Wrapper<String> string) throws IOException;

	public void unsigned16(Wrapper<Integer> field) throws IOException;
	
	public void unsigned32(Wrapper<Long> field) throws IOException;
	
	public <T extends Enum<T>> void enum32(EnumWrapper<T> field) throws IOException;
	
	public <T extends Enum<T>> void enum8(EnumWrapper<T> field) throws IOException;
	
	public void float32(Wrapper<Float> field) throws IOException;
	
	public void signed32(Wrapper<Integer> field) throws IOException;
	
	public void signed16(Wrapper<Short> field) throws IOException;
	
	public void signed8(Wrapper<Byte> field) throws IOException;
	
	public void string32(Wrapper<Text> field) throws IOException;
	
	public void string16(Wrapper<Text> field) throws IOException;
	
	public void bool32(Wrapper<Boolean> field) throws IOException;
	
	public void division() throws CorruptionException, IOException;
	
	public void unused() throws CorruptionException, IOException;

	public void constString(String field) throws CorruptionException, IOException;
	
	public void constUnsigned16(int field) throws CorruptionException, IOException;
	
	public void constUnsigned32(long field) throws CorruptionException, IOException;
	
	public void constByte(int field) throws CorruptionException, IOException;
	
	public void skip(long length) throws IOException;

	public void constFloat(float expected) throws IOException, CorruptionException;
	
	public interface ArrayTranslation<T> { 	public void translate(T value) throws IOException, CorruptionException; }
	public <T> void array(int length, List<T> list, Supplier<T> factory, ArrayTranslation<T> translation) 
			throws IOException, CorruptionException;
	
	public void bitmap(Wrapper<BufferedImage> image) throws IOException, CorruptionException;
	
	public void end() throws IOException, CorruptionException;
}
