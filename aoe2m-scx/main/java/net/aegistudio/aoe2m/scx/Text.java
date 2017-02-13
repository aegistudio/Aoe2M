package net.aegistudio.aoe2m.scx;

/**
 * A text is a special kind of string that 
 * it should either be empty (length 0) or
 * end with '\0' and '\0' would be counted.
 * 
 * @author aegistudio
 */

public final class Text {
	public final long length;
	public final String string;
	
	public Text(String string) {
		if(string.length() > 0 && !string.endsWith("\0")) {
			this.length = string.length() + 1;
			this.string = string.concat("\0");
		}
		else {
			this.length = string.length();
			this.string = string;
		}
	}
	
	public Text() {		this("");	}
	
	public String string() {
		return this.string;
	}
	
	public String toString() {
		return string.replace("\0", "\\0");
	}
	
	public int hashCode() {
		return string.hashCode();
	}
	
	public boolean equals(Object another) {
		if(another instanceof String) 
			return string.equals(another);
		else if(another instanceof Text) {
			Text anoText = (Text) another;
			if(anoText.length != length) return false;
			return string.equals(anoText.string);
		}
		return false;
	}
}
