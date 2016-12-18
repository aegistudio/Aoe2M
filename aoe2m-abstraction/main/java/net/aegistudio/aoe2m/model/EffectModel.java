package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.trigger.EffectPo;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;

public interface EffectModel {
	public void marshal(Scenario sceario, TriggerPo trigger, 
			EffectPo condition)	throws Aoe2mException;
}
