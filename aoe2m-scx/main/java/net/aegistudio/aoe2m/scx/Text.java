package net.aegistudio.aoe2m.scx;

public final class Text {
	public final long length;
	public final String string;
	
	public Text(String string) {
		if(string.length() == 0) {
			this.length = 0;
			this.string = string;
		}
		else {
			this.length = string.length() + 1;
			this.string = string.concat("\0");
		}
	}
	
	public Text(long length, String string) {
		this.length = length;
		this.string = string;
	}
	
	public Text() {		this("");	}
	
	public String toString() {
		return string.replace("\0", "\\0");
	}
}
