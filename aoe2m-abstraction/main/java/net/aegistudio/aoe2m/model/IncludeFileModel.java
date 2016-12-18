package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.IncludeSection;
import net.aegistudio.aoe2m.scx.Scenario;

public interface IncludeFileModel {
	public void marshal(Scenario sceario, IncludeSection section) 
			throws Aoe2mException;
}
