package net.aegistudio.aoe2m.opnagedb;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import net.aegistudio.aoe2m.assetdba.SlpImage;
import net.aegistudio.aoe2m.assetdba.SlpSubImage;

public class OpgSlpImage implements SlpImage {
	protected File image;
	protected int width, height;
	public int width() {	return width;	}
	public int height() {	return height;	}
	
	protected SlpSubImage[] subImages;
	
	public static Supplier<SlpImage> open(File root, String name) {
		return () -> { try {
			return new OpgSlpImage(root, name);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}};
	}
	
	public OpgSlpImage(File root, String name) throws IOException {
		image = new File(root, name + ".png");
		File descriptor = new File(root, name + ".docx");

		try(BufferedReader reader = new BufferedReader(new FileReader(descriptor))) {
			this.subImages = reader.lines()
				.filter(CsvFilter::filter)
				.map(CsvFilter::map)
				.map(FunctionWrapper.highOrder(Integer::parseInt, Integer[]::new))
				.map(numbers -> new SlpSubImage(numbers[0], numbers[1], 
							numbers[2], numbers[3], numbers[4], numbers[5]))
				.filter(Objects::nonNull)
				.toArray(SlpSubImage[]::new);
		}
		calculateSize();
	}
	
	protected void calculateSize() throws IOException {
		BufferedImage bimage = image();
		width = bimage.getWidth();
		height = bimage.getHeight();
	}
	
	public BufferedImage image() {
		try {
			return ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SlpSubImage[] subTextures() {
		return subImages;
	}
}
