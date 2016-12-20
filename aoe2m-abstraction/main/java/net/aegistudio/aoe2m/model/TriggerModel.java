package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;

public interface TriggerModel extends ModelObject<TriggerModel> {
	public ListModelObject<ConditionModel> conditions();
	
	public ListModelObject<EffectModel> effects();
	
	public void marshal(Scenario sceario, TriggerPo map)
			throws Aoe2mException;
}
