package net.aegistudio.aoe2m.scx.map;

import net.aegistudio.aoe2m.scx.Wrapper;

public class TerrianPo extends Wrapper<Byte> {
	public byte[][] terrianValue;
	public TerrianPo(int width, int height) {
		super((byte) 0);
		this.terrianValue = new byte[width][height];
	}
	
	int cursorX = 0, cursorY = 0;
	public void setCursor(int cursorX, int cursorY) {
		this.cursorX = cursorX;
		this.cursorY = cursorY;
	}
	
	public void setValue(Byte b) {
		this.terrianValue[cursorX][cursorY] = b;
	}
	
	public Byte getValue() {
		return this.terrianValue[cursorX][cursorY];
	}
}
