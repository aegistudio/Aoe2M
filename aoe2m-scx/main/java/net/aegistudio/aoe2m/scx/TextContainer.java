package net.aegistudio.aoe2m.scx;

import net.aegistudio.uio.wrap.Container;
import net.aegistudio.uio.wrap.Transform;
import net.aegistudio.uio.Wrapper;

public class TextContainer extends Container<Text> {
	protected Wrapper<String> stringWrapper;
	
	public TextContainer(Text text) {
		super(text);
		this.stringWrapper = new Transform<Text, String>(
				this, Text::string, Text::new);
	}
	
	public TextContainer() {
		this(new Text());
	}
	
	public Wrapper<String> stringWrapper() {
		return stringWrapper;
	}
}
