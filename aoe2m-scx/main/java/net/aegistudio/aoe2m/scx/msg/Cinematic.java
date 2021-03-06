package net.aegistudio.aoe2m.scx.msg;

import java.awt.image.BufferedImage;

import net.aegistudio.uio.wrap.Container;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.aoe2m.scx.TextContainer;

public class Cinematic {
	public TextContainer pregame = new TextContainer();
	public TextContainer victory = new TextContainer();
	public TextContainer loss = new TextContainer();
	public TextContainer background = new TextContainer();
	public Wrapper<BufferedImage> bitmap = new Container<>(null);
}
