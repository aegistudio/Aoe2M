package net.aegistudio.aoe2m.scx.meta;

import net.aegistudio.aoe2m.scx.Text;
import net.aegistudio.aoe2m.scx.Wrapper;

public class MetadataPo {
	public EnumVersion version;
	
	public long lastSavedTimestamp;
	
	public Text scenarioInstruction;
	
	public int playerCount;
	
	public Wrapper<Long> nextUnitId = new Wrapper<>(0l);
	
	public Wrapper<Text> originalFileName = new Wrapper<>(new Text());
}
