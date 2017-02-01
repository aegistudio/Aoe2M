package net.aegistudio.aoe2m.scx.msg;

import java.awt.image.BufferedImage;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Text;
import net.aegistudio.aoe2m.Wrapper;

public class Cinematic {
	public Wrapper<Text> pregame = new Container<>(new Text());
	public Wrapper<Text> victory = new Container<>(new Text());
	public Wrapper<Text> loss = new Container<>(new Text());
	public Wrapper<Text> background = new Container<>(new Text());
	public Wrapper<BufferedImage> bitmap = new Container<>(null);
}
