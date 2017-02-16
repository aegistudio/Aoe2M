package net.aegistudio.aoe2m.slp.printer;

import java.awt.Color;
import java.awt.Graphics;

import net.aegistudio.aoe2m.pal.Palette;
import net.aegistudio.aoe2m.slp.Outline;

public class GraphicsPrinter extends AbstractPrinter {
	public final Graphics graphics;
	public final Outline outline;
	
	protected int xpos, ypos;
	
	public GraphicsPrinter(Graphics graphics, Outline outline,
			Palette normal, Palette player, 
			Color transparent, Color shadow, 
			Color obstruct1,Color obstruct2) {
		
		super(normal, player, transparent, shadow, obstruct1, obstruct2);
		this.graphics = graphics;
		this.outline = outline;
		
		this.ypos = -1;	endl();
	}

	@Override
	public void endl() {
		ypos ++;
		if(ypos < outline.padding.size())
			xpos = outline.padding.get(ypos).left.get();
		else xpos = 0;
	}

	@Override
	protected void put(Color color) {
		graphics.setColor(color);
		graphics.fillRect(xpos, ypos, 1, 1);
		xpos ++;
	}
}
