package net.aegistudio.aoe2m;

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
