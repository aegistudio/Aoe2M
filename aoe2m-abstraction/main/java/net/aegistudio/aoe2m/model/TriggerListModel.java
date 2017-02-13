package net.aegistudio.aoe2m.model;

import java.util.List;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;

public interface TriggerListModel extends ListModelObject<TriggerModel>, ModelObject<TriggerListModel> {
	public void marshal(Scenario sceario, List<TriggerPo> triggers)
			throws Aoe2mException;
}
