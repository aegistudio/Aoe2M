package net.aegistudio.aoe2m.scx.msg;

import java.awt.image.BufferedImage;

import net.aegistudio.aoe2m.scx.Text;
import net.aegistudio.aoe2m.scx.Wrapper;

public class Cinematic {
	public Wrapper<Text> pregame = new Wrapper<>(new Text());
	public Wrapper<Text> victory = new Wrapper<>(new Text());
	public Wrapper<Text> loss = new Wrapper<>(new Text());
	public Wrapper<Text> background = new Wrapper<>(new Text());
	
	public Wrapper<Boolean> bitmapIncluded = new Wrapper<>(false);
	public Wrapper<Long> width = new Wrapper<>(0l), height = new Wrapper<>(0l);
	
	public BufferedImage bitmap;
}
