package net.aegistudio.aoe2m.drs.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.drs.Archive;
import net.aegistudio.aoe2m.drs.TableEntry;
import net.aegistudio.aoe2m.pal.Palette;
import net.aegistudio.aoe2m.ra.RandomAccessible;
import net.aegistudio.aoe2m.slp.CommandTable;
import net.aegistudio.aoe2m.slp.Frame;
import net.aegistudio.aoe2m.slp.ImagePrinter;
import net.aegistudio.aoe2m.slp.Outline;
import net.aegistudio.aoe2m.slp.Picture;
import net.aegistudio.aoe2m.slp.printer.GraphicsPrinter;
import net.aegistudio.aoe2m.slp.printer.NullPrinter;
import net.aegistudio.aoe2m.slp.printer.SubscribePrinter;

public class SlpRenderView extends JPanel {
	private static final long serialVersionUID = 1L;

	public final Archive interfac;
	public final Picture picture;
	public final Translator translator;
	public final RandomAccessible access;
	
	public final JLabel imageView;
	public final JSpinner frame;
	protected Palette palette;
	
	public final JCheckBox normal, player, obstruct, origin;
	
	public SlpRenderView(Archive interfac, Picture picture, 
			Translator translator, RandomAccessible access) {
		super.setLayout(new BorderLayout());
		
		this.interfac = interfac;
		this.picture = picture;
		this.translator = translator;
		this.access = access;
		
		imageView = new JLabel();
		imageView.setHorizontalAlignment(JLabel.CENTER);
		super.add(BorderLayout.CENTER, new JScrollPane(imageView));
		
		JPanel controlPanel = new JPanel();
		super.add(BorderLayout.SOUTH, controlPanel);
		
		JLabel frameTag = new JLabel("Frame:");
		controlPanel.add(frameTag);
		
		frame = new JSpinner();
		SpinnerModel frameModel = new SpinnerNumberModel(
				1, 1, picture.frames.size(), 1);
		frame.setModel(frameModel);
		frame.setVerifyInputWhenFocusTarget(true);
		frame.addChangeListener(cevent -> fireRenderFrame());
		
		frame.setPreferredSize(new Dimension(60, 25));
		controlPanel.add(frame);
		
		JLabel frameRange = new JLabel(" / " + picture.frames.size());
		controlPanel.add(frameRange);
		
		normal = new JCheckBox("Normal");
		normal.setSelected(true);
		normal.addChangeListener(cevent -> fireRenderFrame());
		controlPanel.add(normal);
		
		player = new JCheckBox("Player");
		player.setSelected(true);
		player.addChangeListener(cevent -> fireRenderFrame());
		controlPanel.add(player);
		
		obstruct = new JCheckBox("Obstruct");
		obstruct.setSelected(true);
		obstruct.addChangeListener(cevent -> fireRenderFrame());
		controlPanel.add(obstruct);
		
		origin = new JCheckBox("Origin");
		origin.setSelected(false);
		origin.addActionListener(cevent -> fireRenderFrame());
		controlPanel.add(origin);
		
		JLabel paletteTag = new JLabel("Palette: ");
		controlPanel.add(paletteTag);
		
		JSpinner paletteSelector = new JSpinner();
		SpinnerModel paletteModel = new SpinnerNumberModel(
				50500, 50500, 50999, 1);
		paletteSelector.setModel(paletteModel);
		paletteSelector.addChangeListener(cevent -> {
			changePalette((int)paletteSelector.getValue());
			fireRenderFrame();
		});
		controlPanel.add(paletteSelector);
		
		changePalette(50500);
		
		renderFrame(0);
	}
	
	public void fireRenderFrame() {
		renderFrame((int)frame.getValue() - 1);
	}
	
	public void changePalette(int id) {
		try {
			palette = new Palette();
			TableEntry paletteEntry = interfac.getEntry(0, id);
			InputStream input = new ByteArrayInputStream(
					interfac.open(paletteEntry));
			palette.read(input);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private GraphicsPrinter createGraphicsPrinter(Graphics graphics, Outline outline) {
		return new GraphicsPrinter(graphics, outline, 
				palette, palette, new Color(0, 0, 0, 0), 
				new Color(0.5f, 0.5f, 0.5f, 0.5f), Color.BLUE, Color.BLUE);
	}
	
	public void renderFrame(int whichFrame) {
		try {
			Frame frame = picture.frames.get(whichFrame);
			BufferedImage image = new BufferedImage(frame.width.getValue(), 
					frame.height.getValue(), BufferedImage.TYPE_4BYTE_ABGR);
			
			Outline outline = new Outline();
			CommandTable command = new CommandTable();
			frame.seek(translator, access, outline, command);
			
			Graphics graphics = image.getGraphics();

			ImagePrinter player = this.player.isSelected()?
					createGraphicsPrinter(graphics, outline) : new NullPrinter();
			
			ImagePrinter normal = this.normal.isSelected()?
					createGraphicsPrinter(graphics, outline) : new NullPrinter();
					
			ImagePrinter obstruct = this.obstruct.isSelected()?
					createGraphicsPrinter(graphics, outline) : new NullPrinter();
			
			SubscribePrinter painter = new SubscribePrinter(normal, player, obstruct);
			command.render(painter);
			
			if(origin.isSelected()) {
				int ox = frame.originX.getValue();
				int oy = frame.originY.getValue();
				int w = frame.width.getValue();
				int h = frame.height.getValue();
				
				graphics.setColor(Color.RED);
				graphics.drawLine(0, oy, w, oy);
				graphics.drawLine(ox, 0, ox, h);
			}
			
			image.flush();
			
			imageView.setIcon(new ImageIcon(image));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
