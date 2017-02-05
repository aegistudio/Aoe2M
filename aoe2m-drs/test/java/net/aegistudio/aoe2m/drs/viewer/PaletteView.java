package net.aegistudio.aoe2m.drs.viewer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import net.aegistudio.aoe2m.pal.Palette;

public class PaletteView extends JLabel {
	private static final long serialVersionUID = 1L;
	
	public PaletteView(Palette palette, int cellSize, int rowCell) {
		
		int rowCount = (palette.palette.size() + rowCell - 1) / rowCell;
		int columnCount = rowCell;
		
		BufferedImage paletteRender = new BufferedImage(
				rowCount * cellSize + 1, columnCount * cellSize, 
				BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics g = paletteRender.getGraphics();
		
		Transcribe: for(int i = 0; i < rowCount; i ++)
			for(int j = 0; j < columnCount; j ++) {
				int current = i * rowCell + j;
				if(current >= palette.palette.size())
					break Transcribe;
				
				g.setColor(palette.palette.get(current));
				g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
			}
		
		super.setIcon(new ImageIcon(paletteRender));
	}
}
