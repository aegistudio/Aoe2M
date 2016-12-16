package net.aegistudio.aoe2m.scx;

public final class String16 {
	public final int length;
	public final String string;
	
	public String16(String string) {
		if(string.length() == 0) {
			this.length = 0;
			this.string = string;
		}
		else {
			this.length = string.length() + 1;
			this.string = string.concat("\0");
		}
	}
	
	public String16(int length, String string) {
		this.length = length;
		this.string = string;
	}
	
	public String toString() {
		return string.replace("\0", "\\0");
	}
}
