package net.aegistudio.aoe2m.scx.msg;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator.Fallbackable;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.stream.BinaryInputStream;
import net.aegistudio.uio.stream.BinaryOutputStream;

public class ImageFallback implements Fallbackable {
	public final Wrapper<BufferedImage> image;
	public ImageFallback(Wrapper<BufferedImage> bmp) {
		this.image = bmp;
	}
	
	public static final int REALLOC_SIZE = 4096;
	
	@SuppressWarnings("resource")
	public void read(InputStream inputStream) throws IOException {
		byte[] BITMAPINFOHDR = new byte[40];
		if(inputStream.read(BITMAPINFOHDR) != 40) throw new EOFException();
		BinaryInputStream tempInputStream = new BinaryInputStream(
				new ByteArrayInputStream(BITMAPINFOHDR), "utf8");
		
		CorruptException.check(40, tempInputStream.readSigned32());
		tempInputStream.skip(4 + 4 + 2 + 2 + 4);
		int size = tempInputStream.readSigned32();
		tempInputStream.skip(4 + 4);
		int paletteSize = tempInputStream.readSigned32() * 4;
		
		// Buffer reallocation.
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		BinaryOutputStream tempFieldOutput = new BinaryOutputStream(output);
		
		// BITMAPCOREHDR
		tempFieldOutput.write('B'); 
		tempFieldOutput.write('M'); 
		tempFieldOutput.write32(14 + 40 + size + paletteSize);
		tempFieldOutput.write32(0);
		tempFieldOutput.write32(14 + 40 + paletteSize);
		
		//BITMAPINFOHDR
		tempFieldOutput.write(BITMAPINFOHDR);
		
		//BITMAPBODY
		int remained = size + paletteSize;
		byte[] realloc = new byte[REALLOC_SIZE];
		
		while(remained > 0) {
			int readSize = inputStream.read(
					realloc, 0, Math.min(remained, REALLOC_SIZE));
			
			tempFieldOutput.write(realloc, 0, readSize);
			tempFieldOutput.flush();
			remained = remained - readSize;
		}

		// Finish
		byte[] result = output.toByteArray();
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(result));
		if(bufferedImage == null) CorruptException.check("Bitmap", "Unknown");
		
		image.set(bufferedImage);
	}

	@Override
	public void write(OutputStream output) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(image.get(), "bmp", buffer);
		byte[] full = buffer.toByteArray();
		byte[] trim = new byte[full.length - 14];
		System.arraycopy(full, 14, trim, 0, trim.length);
		output.write(trim);
	}
}
