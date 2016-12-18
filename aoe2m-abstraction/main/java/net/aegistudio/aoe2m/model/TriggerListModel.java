package net.aegistudio.aoe2m.model;

import java.util.Set;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.trigger.OrderedList;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;

public interface TriggerListModel extends ModelObject {
	public TriggerModel get(String name);
	public void set(String name, TriggerModel triggerModel);
	public void remove(String name);
	public Set<String> keySet();
	
	public void marshal(Scenario sceario, OrderedList<TriggerPo> triggers)
			throws Aoe2mException;
}
