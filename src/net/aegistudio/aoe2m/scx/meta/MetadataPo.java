package net.aegistudio.aoe2m.scx.meta;

import net.aegistudio.aoe2m.scx.String16;
import net.aegistudio.aoe2m.scx.String32;
import net.aegistudio.aoe2m.scx.Wrapper;

public class MetadataPo {
	public EnumVersion version;
	
	public long lastSavedTimestamp;
	
	public String32 scenarioInstruction;
	
	public int playerCount;
	
	public Wrapper<Long> nextUnitId = new Wrapper<Long>(0l);
	
	public Wrapper<String16> originalFileName = new Wrapper<String16>(new String16(0, ""));
}
