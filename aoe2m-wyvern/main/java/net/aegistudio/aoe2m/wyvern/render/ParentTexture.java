package net.aegistudio.aoe2m.wyvern.render;

public interface ParentTexture extends Texture {
	public Texture get(int index);
	
	public int count();
}
