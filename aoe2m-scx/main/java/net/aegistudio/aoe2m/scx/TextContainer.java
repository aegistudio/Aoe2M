package net.aegistudio.aoe2m.scx;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Transform;
import net.aegistudio.aoe2m.Wrapper;

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
