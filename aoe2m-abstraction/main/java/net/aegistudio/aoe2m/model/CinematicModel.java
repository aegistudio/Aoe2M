package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.msg.Cinematic;

public interface CinematicModel extends ModelObject {	
	public void marshal(Scenario sceario, Cinematic cinematic) 
			throws Aoe2mException;
}
