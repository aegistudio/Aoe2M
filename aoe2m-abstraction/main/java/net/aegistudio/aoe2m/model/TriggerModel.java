package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;

public interface TriggerModel {
	public ConditionModel getCondition(int index);
	public void addCondition(ConditionModel condition);
	public void removeCondition(int index);
	public int conditionCount();
	
	public EffectModel getEffect(int index);
	public void addEffect(EffectModel effect);
	public void removeEffect(int index);
	public int effectCount();
	
	public void marshal(Scenario sceario, TriggerPo map)
			throws Aoe2mException;
}
