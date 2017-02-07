package net.aegistudio.aoe2m.slp.printer;

import net.aegistudio.aoe2m.slp.ImagePrinter;

public class SubscribePrinter implements ImagePrinter {
	public final ImagePrinter normal;
	public final ImagePrinter player;
	public final ImagePrinter obstruct;
	
	public SubscribePrinter(ImagePrinter normal, 
			ImagePrinter player, ImagePrinter obstruct) {
		this.normal = normal;
		this.player = player;
		this.obstruct = obstruct;
	}
	
	@Override
	public void transparent() {
		this.normal.transparent();
		this.player.transparent();
		this.obstruct.transparent();
	}

	@Override
	public void normal(byte index) {
		this.normal.normal(index);
		this.player.transparent();
		this.obstruct.transparent();
	}

	@Override
	public void player(byte index) {
		this.normal.transparent();
		this.player.player(index);
		this.obstruct.transparent();
	}

	@Override
	public void shadow() {
		this.normal.shadow();
		this.player.transparent();
		this.obstruct.transparent();
	}

	@Override
	public void obstruct1() {
		this.normal.transparent();
		this.player.transparent();
		this.obstruct.obstruct1();
	}

	@Override
	public void obstruct2() {
		this.normal.transparent();
		this.player.transparent();
		this.obstruct.obstruct2();
	}

	@Override
	public void endl() {
		this.normal.endl();
		this.player.endl();
		this.obstruct.endl();
	}

}
