package net.aegistudio.aoe2m.assetdba;

public class GraphicsGamedata {
	public int id;
	public String name0, name1;
	public SlpImage slp;
	public EnumLayer layer;
	
	public int playerColor, adaptColor;
	public int replay;
	public String soundId;	
	public boolean attackSoundUsed;
	
	public int frameCount, angleCount;
	public double frameRate, replayDelay;
	public int sequenceType;
	public int mirroringMode;
	
	public GraphicsDelta[] deltas;
}
