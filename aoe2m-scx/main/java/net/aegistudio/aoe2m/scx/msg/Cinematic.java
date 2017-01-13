package net.aegistudio.aoe2m.scx.msg;

import java.awt.image.BufferedImage;

import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.Text;

public class Cinematic {
	public Wrapper<Text> pregame = new Wrapper<>(new Text());
	public Wrapper<Text> victory = new Wrapper<>(new Text());
	public Wrapper<Text> loss = new Wrapper<>(new Text());
	public Wrapper<Text> background = new Wrapper<>(new Text());
	public Wrapper<BufferedImage> bitmap = new Wrapper<>(null);
}
