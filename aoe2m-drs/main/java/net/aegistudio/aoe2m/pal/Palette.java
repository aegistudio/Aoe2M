package net.aegistudio.aoe2m.pal;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.CorruptionException;

public class Palette {	
	public static final String MAGIC_NUMBER = "JASC-PAL";
	public static final String MAGIC_VERSION = "0100";
	
	public final List<Color> palette = new ArrayList<>();
	public Color[] getPalette() {
		return palette.toArray(new Color[0]);
	}
	
	public void read(InputStream inputStream) throws IOException, CorruptionException {
		try(WindowsLineReader line = new WindowsLineReader(inputStream)) {
			String magicNumber = line.readLine();
			if(magicNumber.equals(MAGIC_NUMBER)) 
				throw new CorruptionException(magicNumber, MAGIC_NUMBER);
			
			String magicVersion = line.readLine();
			if(magicVersion.equals(MAGIC_VERSION))
				throw new CorruptionException(magicVersion, MAGIC_VERSION);
			
			int countLines = Integer.parseInt(line.readLine());
			for(int i = 0; i < countLines; i ++) {
				String currentColor = line.readLine();
				String[] colorTuple = currentColor.split(" ");
				int r = Integer.parseInt(colorTuple[0]);
				int g = Integer.parseInt(colorTuple[1]);
				int b = Integer.parseInt(colorTuple[2]);
				palette.add(new Color(r, g, b));
			}
		}
	}
	
	public void write(OutputStream outputStream) throws IOException, CorruptionException {
		try(WindowsLineWriter line = new WindowsLineWriter(outputStream)) {
			line.writeLine(MAGIC_NUMBER);
			line.writeLine(MAGIC_VERSION);
			line.writeLine(Integer.toString(palette.size()));
			for(Color color : palette) {
				String currentColor = String.format("%d %d %d", 
						color.getRed(), color.getGreen(), color.getBlue());
				line.writeLine(currentColor);
			}	
		}
	}
}
