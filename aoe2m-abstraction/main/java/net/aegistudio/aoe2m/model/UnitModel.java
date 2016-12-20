package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.map.MapPo;
import net.aegistudio.aoe2m.scx.map.UnitPo;

public interface UnitModel extends ModelObject<UnitModel> {
	public void marshal(Scenario sceario, MapPo map, UnitPo unit)
			throws Aoe2mException;
}
