package net.aegistudio.aoe2m.opnagedb;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import javax.imageio.ImageIO;

import net.aegistudio.aoe2m.assetdba.SlpImage;
import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.uio.media.Storage;

public class OpgSlpImage implements SlpImage {
	protected final Storage image;
	protected final SlpSubImage[] subImages;
	protected final Runnable initArchive, readyArchive;
	
	public static SlpImage open(Runnable initArchive, Runnable readyArchive, Storage root, String name) {
		try {
			return new OpgSlpImage(initArchive, readyArchive, root, name);
		} catch (IOException e) {
			return null;
		}
	}
	
	public OpgSlpImage(Runnable initArchive, Runnable readyArchive, Storage root, String name) throws IOException {
		this.initArchive = initArchive;
		this.readyArchive = readyArchive;
		this.image = root.open(name + ".png");
		Storage descriptor = root.open(name + ".docx");

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(descriptor.read()))) {
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
			initArchive.run();
			BufferedImage imageResult = ImageIO.read(image.read());
			readyArchive.run();
			return imageResult;
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
