package net.aegistudio.aoe2m.opnagedb;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.PlayerPalette;

public class OpgPlayerPalette extends PlayerPalette {
	public OpgPlayerPalette(File root) throws IOException {
		File descriptor = new File(root, "player_palette.docx");

		try(BufferedReader reader = new BufferedReader(new FileReader(descriptor))) {
			this.items = reader.lines()
				.filter(CsvFilter::filter)
				.map(CsvFilter::map)
				.map(FunctionWrapper.highOrder(Integer::parseInt, Integer[]::new))
				.map(numbers -> new Color(numbers[1], numbers[2], numbers[3], numbers[4]))
				.toArray(Color[]::new);
		}
		
		super.allLength = items.length;
		super.subLength = 8;
	}
	
	public Color[] palette() {
		return items;
	}
	
	public int query(int r, int g, int b, int a) {
		for(int i = 0; i < subLength; i ++) {
			int dr = items[i].getRed() - r;
			int dg = items[i].getGreen() - g;
			int db = items[i].getBlue() - b;
			
			if(dr == 0 && dg == 0 && db == 0)
				return i;
		}
		return -1;
	}
	
	public int query(Color pick) {
		return query(pick.getRed(), pick.getGreen(), pick.getBlue(), pick.getAlpha());
	}
}
