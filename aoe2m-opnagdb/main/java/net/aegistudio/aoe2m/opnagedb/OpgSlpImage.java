package net.aegistudio.aoe2m.opnagedb;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import net.aegistudio.aoe2m.assetdba.SlpImage;
import net.aegistudio.aoe2m.assetdba.SlpSubImage;

public class OpgSlpImage implements SlpImage {
	protected final File image;
	protected final SlpSubImage[] subImages;
	protected final Runnable perfLog;
	
	public static SlpImage open(Runnable perfLog, File root, String name) {
		try {
			return new OpgSlpImage(perfLog, root, name);
		} catch (IOException e) {
			return null;
		}
	}
	
	public OpgSlpImage(Runnable perfLog, File root, String name) throws IOException {
		this.perfLog = perfLog;
		this.image = new File(root, name + ".png");
		if(!image.exists()) throw new FileNotFoundException(name);
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
	}
	
	public BufferedImage open() {
		try {
			perfLog.run();
			return ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
	public BufferedImage normal() {
		return open();
	}

	@Override
	public BufferedImage player() {
		return open();
	}
	
	@Override
	public BufferedImage obstruct() {
		return open();
	}
	
	@Override
	public SlpSubImage[] subTextures() {
		return subImages;
	}
}
