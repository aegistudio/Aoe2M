package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.impcall.ValueObserver;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.trigger.ConditionPo;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;

public interface ConditionModel extends ModelObject<ConditionModel> {
	public ValueObserver<ConditionModel> self();
	
	public void marshal(Scenario sceario, TriggerPo trigger, 
			ConditionPo condition)	throws Aoe2mException;
}
