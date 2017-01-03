package net.aegistudio.aoe2m.opnagedb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import net.aegistudio.aoe2m.assetdba.blob.SlpSubTexture;
import net.aegistudio.aoe2m.assetdba.blob.SlpTexture;

public class OpgSlpTexture implements SlpTexture {
	protected File image;
	protected SlpSubTexture[] subTextures;
	
	public OpgSlpTexture(File root, String name) throws IOException {
		image = new File(root, name + ".png");
		File descriptor = new File(root, name + ".docx");

		try(BufferedReader reader = new BufferedReader(new FileReader(descriptor))) {
			this.subTextures = reader.lines().map(line -> {
				if(line.length() == 0) return null;
				if(line.startsWith("#")) return null;
				Integer[] numbers = Arrays.stream(line.split(","))
						.map(Double::parseDouble).toArray(Integer[]::new);
				
				return new SlpSubTexture(numbers[0], numbers[1], 
						numbers[2], numbers[3], numbers[4], numbers[5]);
			}).filter(slp -> slp != null).toArray(SlpSubTexture[]::new);
		}
	}
	
	public InputStream image() throws FileNotFoundException {
		return new FileInputStream(image);
	}

	@Override
	public SlpSubTexture[] subTextures() {
		return subTextures;
	}
}
