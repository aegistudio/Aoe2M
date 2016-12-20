package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.map.MapPo;

public interface TerrainModel extends ModelObject<TerrainModel> {
	public UnitModel getUnit(int index);
	
	public void addUnit(UnitModel unit);
	
	public void removeUnit(int index);
	
	public int unitCount();
	
	public int terrain(int x, int y);
	public int elevation(int x, int y);
	
	public void marshal(Scenario sceario, MapPo map)
			throws Aoe2mException;
}
