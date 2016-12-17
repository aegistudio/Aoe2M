package net.aegistudio.aoe2m.scx.meta;

import java.util.Date;

import net.aegistudio.aoe2m.scx.Text;
import net.aegistudio.aoe2m.scx.Wrapper;

public class MetadataPo {
	public EnumVersion version = EnumVersion.AGE_OF_EMPIRE_II_THE_CONQUEROR;
	
	public long lastSavedTimestamp;
	public void touch() {
		this.lastSavedTimestamp = new Date().getTime() / 1000;
	}
	{	touch();	}
	
	public int playerCount = 2;
	
	public Wrapper<Long> nextUnitId = new Wrapper<>(0l);
	
	public Wrapper<Text> originalFileName = new Wrapper<>(new Text());
}
