package net.aegistudio.aoe2m.wyvern.asset;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

public class SlpTextureAsset implements Supplier<BufferedImage> {
	File image, descriptor;
	
	public SlpTextureAsset(File root, String name) throws FileNotFoundException {
		image = new File(root, name + ".png");
		descriptor = new File(root, name + ".docx");
	}
	
	@Override
	public BufferedImage get() {
		try {
			return ImageIO.read(image());
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public InputStream image() throws FileNotFoundException {
		return new FileInputStream(image);
	}
	
	public InputStream descriptor() throws FileNotFoundException {
		return new FileInputStream(descriptor);
	}
}
