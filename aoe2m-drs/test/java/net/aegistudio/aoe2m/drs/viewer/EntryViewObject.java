package net.aegistudio.aoe2m.drs.viewer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.drs.Archive;
import net.aegistudio.aoe2m.drs.TableEntry;
import net.aegistudio.aoe2m.io.FieldInputTranslator;
import net.aegistudio.aoe2m.pal.Palette;
import net.aegistudio.aoe2m.ra.AccessInputStream;
import net.aegistudio.aoe2m.ra.ByteBufferAdapter;
import net.aegistudio.aoe2m.ra.RandomAccessible;
import net.aegistudio.aoe2m.slp.Picture;

public class EntryViewObject implements ViewObject {
	public final Archive archive;
	public final TableViewObject parent;
	public final TableEntry entry;
	public final Archive interfac;
	
	public EntryViewObject(Archive archive, TableViewObject parent, 
			Archive interfac, TableEntry entry) {
		this.archive = archive;
		this.parent = parent;
		this.entry = entry;
		this.interfac = interfac;
	}
	
	public String toString() {
		return entry.id.getValue() + "." + parent.getFormat();
	}

	@Override
	public List<ViewObject.Entry> openView() {
		List<ViewObject.Entry> result = new ArrayList<>();
		try {
			System.out.println("Reading file " + toString() + "...");
			byte[] content = archive.open(entry);
			System.out.println("Finished file " + toString() + "!");
			
			if(parent.getFormat().equals("bin")) {
				byte[] head = new byte[16];
				System.arraycopy(content, 0, head, 0, head.length);
				String text = new String(head);
				
				// Bmp file.
				if(text.startsWith("BM")) {
					BufferedImage image = ImageIO.read(new ByteArrayInputStream(content));
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(image));
					
					result.add(new ViewObject.Entry(
							"Bmp", new JScrollPane(label)));
				}
				
				// Just a plain text.
				else {
					// A palette.
					if(text.startsWith("JASC-PAL")) {
						Palette palette = new Palette();
						palette.read(new ByteArrayInputStream(content));
						result.add(new ViewObject.Entry(
								"Palette", new PaletteView(palette, 16, 16)));
					}
								
					result.add(new ViewObject.Entry(
							"Plain", new JScrollPane(
									new JTextArea(new String(content)))));
				}
			}
			
			if(parent.getFormat().equals("slp")) try {
				Picture picture = new Picture();
				
				RandomAccessible randomAccess = new ByteBufferAdapter(content);
				InputStream inputStream = new AccessInputStream(randomAccess);
				Translator translator = new FieldInputTranslator(inputStream, "gbk");
				picture.translate(translator);
				
				result.add(new ViewObject.Entry("Slp", 
						new SlpRenderView(interfac, picture, translator, randomAccess)));
			} catch(Exception e) {	e.printStackTrace(); }
			
			result.add(new ViewObject.Entry(
					"Hex", new HexDumpView(content, 16, 2)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
