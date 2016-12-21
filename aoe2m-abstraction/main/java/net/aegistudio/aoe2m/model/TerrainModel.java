package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.map.MapPo;

public interface TerrainModel extends ModelObject<TerrainModel> {
	public ListModelObject<UnitModel> units();
	
	public void marshal(Scenario sceario, MapPo map)
			throws Aoe2mException;
}
