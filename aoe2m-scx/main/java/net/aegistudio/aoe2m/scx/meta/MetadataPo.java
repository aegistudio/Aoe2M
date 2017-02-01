package net.aegistudio.aoe2m.scx.meta;

import java.util.Date;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Text;
import net.aegistudio.aoe2m.Wrapper;

public class MetadataPo {
	public EnumVersion version = EnumVersion.AGE_OF_EMPIRE_II_THE_CONQUEROR;
	
	public long lastSavedTimestamp;
	public void touch() {
		this.lastSavedTimestamp = new Date().getTime() / 1000;
	}
	{	touch();	}
	
	public int playerCount = 2;
	
	public Wrapper<Long> nextUnitId = new Container<>(0l);
	
	public Wrapper<Text> originalFileName = new Container<>(new Text());
}
