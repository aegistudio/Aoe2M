package net.aegistudio.aoe2m.scx.msg;

import java.awt.image.BufferedImage;

import net.aegistudio.aoe2m.scx.String16;
import net.aegistudio.aoe2m.scx.Wrapper;

public class Cinematic {
	public Wrapper<String16> pregame = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<String16> victory = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<String16> loss = new Wrapper<String16>(new String16(0, ""));
	public Wrapper<String16> background = new Wrapper<String16>(new String16(0, ""));
	
	public Wrapper<Boolean> bitmapIncluded = new Wrapper<Boolean>(false);
	public Wrapper<Long> width = new Wrapper<Long>(0l), height = new Wrapper<Long>(0l);
	
	public BufferedImage bitmap;
}
