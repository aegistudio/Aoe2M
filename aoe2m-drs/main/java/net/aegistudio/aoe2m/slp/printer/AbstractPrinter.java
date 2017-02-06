package net.aegistudio.aoe2m.slp.printer;

import java.awt.Color;

import net.aegistudio.aoe2m.pal.Palette;
import net.aegistudio.aoe2m.slp.ImagePrinter;

public abstract class AbstractPrinter implements ImagePrinter {
	protected abstract void put(Color color);
	
	public abstract void endl();
	
	public final Palette normal;
	public final Palette player;
	public final Color transparent, shadow;
	public final Color obstruct1, obstruct2;
	
	public AbstractPrinter(Palette normal, Palette player, 
			Color transparent, Color shadow, Color obstruct1, Color obstruct2) {
		this.normal = normal;
		this.player = player;
		
		this.transparent = transparent;
		this.shadow = shadow;

		this.obstruct1 = obstruct1;
		this.obstruct2 = obstruct2;
	}

	@Override
	public void transparent() {
		this.put(transparent);
	}

	@Override
	public void normal(byte index) {
		int value = index & 0x00ff;
		this.put(normal.palette.get(value));
	}

	@Override
	public void player(byte index) {
		int value = index & 0x00ff;
		this.put(player.palette.get(value));
	}

	@Override
	public void shadow() {
		this.put(shadow);
	}

	@Override
	public void obstruct1() {
		this.put(obstruct1);
	}

	@Override
	public void obstruct2() {
		this.put(obstruct2);
	}
}