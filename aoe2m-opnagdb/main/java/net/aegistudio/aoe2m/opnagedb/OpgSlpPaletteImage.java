package net.aegistudio.aoe2m.opnagedb;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.function.BiFunction;

import javax.imageio.ImageIO;

import net.aegistudio.aoe2m.assetdba.SlpImage;
import net.aegistudio.uio.media.Storage;

public class OpgSlpPaletteImage extends OpgSlpImage {
	protected final OpgPlayerPalette palette;
	
	public static SlpImage open(Runnable initArchive, Runnable readyArchive, Storage root, String name, OpgPlayerPalette palette) {
		try {
			return new OpgSlpPaletteImage(initArchive, readyArchive, root, name, palette);
		} catch (IOException e) {
			return null;
		}
	}
	
	public OpgSlpPaletteImage(Runnable initArchive, Runnable readyArchive, Storage root, String name, OpgPlayerPalette palette) throws IOException {
		super(initArchive, readyArchive, root, name);
		this.palette = palette;
	}
	
	public BufferedImage open(BiFunction<Color, Boolean, Color> colorFilter) {
		try {
			initArchive.run();
			BufferedImage bufferImage = ImageIO.read(image.read());
			BufferedImage copyImage = new BufferedImage(bufferImage.getWidth(), 
					bufferImage.getHeight(), bufferImage.getType());
			
			WritableRaster rgb = bufferImage.getRaster();
			WritableRaster al = bufferImage.getAlphaRaster();
			
			WritableRaster orgb = copyImage.getRaster();
			WritableRaster oal = copyImage.getAlphaRaster();
			
			for(int i = 0; i < bufferImage.getWidth(); i ++)
				for(int j = 0; j < bufferImage.getHeight(); j ++) {
					int r = rgb.getSample(i, j, 0);
					int g = rgb.getSample(i, j, 1);
					int b = rgb.getSample(i, j, 2);
					int a = al.getSample(i, j, 0);
					
					Color color = new Color(r, g, b, a);
					boolean border = a == 0;
					
					if(i == 0 || i == bufferImage.getWidth() - 1)
						border = true;
					else if(j == 0 || j == bufferImage.getHeight() - 1)
						border = true;
					else {
						boolean left =   al.getSample(i - 1, j, 0) == 0;
						boolean right =  al.getSample(i + 1, j, 0) == 0;
						boolean top =    al.getSample(i, j - 1, 0) == 0;
						boolean bottom = al.getSample(i, j + 1, 0) == 0;
						border = left || right || top || bottom;
					}
					
					Color result = colorFilter.apply(color, border);
					
					orgb.setSample(i, j, 0, result.getRed());
					orgb.setSample(i, j, 1, result.getGreen());
					orgb.setSample(i, j, 2, result.getBlue());
					oal.setSample(i, j, 0, result.getAlpha());
				}
			copyImage.flush();
			readyArchive.run();
			
			return copyImage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);
	public static final Color OPAQUE = new Color(1f, 1f, 1f, 1f);
	public BufferedImage normal() {
		return open((color, border) -> {
			if(palette.query(color) >= 0)
				return TRANSPARENT;
			return color;
		});
	}

	public static final Color[] PALETTE_MAP = new Color[] {
			new Color(0f, 0f, 0f, 1f),	// 0
			new Color(1f, 0f, 0f, 1f),	// 1
			new Color(0f, 1f, 0f, 1f),	// 2
			new Color(1f, 1f, 0f, 1f),	// 3
			new Color(0f, 0f, 1f, 1f),	// 4
			new Color(1f, 0f, 1f, 1f),	// 5
			new Color(0f, 1f, 1f, 1f),	// 6
			new Color(1f, 1f, 1f, 1f),	// 7
	};
	
	@Override
	public BufferedImage player() {
		return open((color, border) -> {
			int paletteIndex = palette.query(color);
			if(paletteIndex >= 0 && !border)
				return PALETTE_MAP[paletteIndex];
			return TRANSPARENT;
		});
	}
	
	@Override
	public BufferedImage obstruct() {
		return open((color, border) -> {
			int paletteIndex = palette.query(color);
			if(paletteIndex >=0 && border) 
				return OPAQUE;
			return TRANSPARENT;
		});
	}
}
