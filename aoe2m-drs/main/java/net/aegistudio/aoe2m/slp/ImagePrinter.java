package net.aegistudio.aoe2m.slp;

public interface ImagePrinter {
	public void transparent();
	
	public void normal(byte index);
	
	public void player(byte index);
	
	public void shadow();
	
	public void obstruct1();
	
	public void obstruct2();
	
	public void endl();
}
