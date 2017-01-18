package net.aegistudio.aoe2m.opnagedb;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpgPlayerPalette {
	protected final Color[] playerPalette;
	
	public OpgPlayerPalette(File root) throws IOException {
		File descriptor = new File(root, "player_palette.docx");

		try(BufferedReader reader = new BufferedReader(new FileReader(descriptor))) {
			this.playerPalette = reader.lines()
				.filter(CsvFilter::filter)
				.map(CsvFilter::map)
				.map(FunctionWrapper.highOrder(Integer::parseInt, Integer[]::new))
				.map(numbers -> new Color(numbers[1], numbers[2], numbers[3], numbers[4]))
				.toArray(Color[]::new);
		}
	}
	
	public Color[] palette() {
		return playerPalette;
	}
	
	public int query(int r, int g, int b, int a) {
		for(int i = 0; i < 8; i ++) {
			int dr = playerPalette[i].getRed() - r;
			int dg = playerPalette[i].getGreen() - g;
			int db = playerPalette[i].getBlue() - b;
			
			if(dr == 0 && dg == 0 && db == 0)
				return i;
		}
		return -1;
	}
	
	public int query(Color pick) {
		return query(pick.getRed(), pick.getGreen(), pick.getBlue(), pick.getAlpha());
	}
}
