package net.aegistudio.aoe2m.drs.viewer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.pal.Palette;
import net.aegistudio.aoe2m.ra.RandomAccessible;
import net.aegistudio.aoe2m.slp.CommandTable;
import net.aegistudio.aoe2m.slp.Frame;
import net.aegistudio.aoe2m.slp.Outline;
import net.aegistudio.aoe2m.slp.Picture;
import net.aegistudio.aoe2m.slp.printer.GraphicsPainter;

public class SlpRenderView extends JLabel {
	private static final long serialVersionUID = 1L;

	public final Palette palette;
	public final Picture picture;
	
	public SlpRenderView(Palette palette, Picture picture, 
			FieldTranslator translator, RandomAccessible access) 
					throws IOException, CorruptionException {
		
		this.palette = palette;
		this.picture = picture;
		
		Frame frame = picture.frames.get(0);
		BufferedImage image = new BufferedImage(frame.width.getValue(), 
				frame.height.getValue(), BufferedImage.TYPE_4BYTE_ABGR);
		
		Outline outline = new Outline();
		CommandTable command = new CommandTable();
		frame.seek(translator, access, outline, command);
		
		GraphicsPainter painter = new GraphicsPainter(image.getGraphics(), outline, 
				palette, palette, new Color(0, 0, 0, 0), 
				new Color(0.5f, 0.5f, 0.5f, 0.5f), Color.BLUE, Color.BLUE);
		command.render(painter);
		image.flush();
		
		super.setIcon(new ImageIcon(image));
	}
}
