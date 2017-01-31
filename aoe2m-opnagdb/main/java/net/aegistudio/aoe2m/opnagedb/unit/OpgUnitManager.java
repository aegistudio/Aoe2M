package net.aegistudio.aoe2m.opnagedb.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;
import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.unit.EnumUnitType;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;
import net.aegistudio.aoe2m.media.Storage;

public class OpgUnitManager implements AssetManager<UnitGamedata> {
	public final int maximal;
	public Map<Integer, OpgUnitGamedata> units = new TreeMap<>();
	
	public OpgUnitManager(int estimatedCount, AssetListener perfLog, Storage civ) throws IOException {
		List<OpgUnitGamedata> unitList = new ArrayList<>();
		build(unitList, perfLog, civ, EnumUnitType.EYE_CANDY, "object");
		build(unitList, perfLog, civ, EnumUnitType.FLAG, "flag");
		build(unitList, perfLog, civ, EnumUnitType.FLAG, "doppelganger");
		build(unitList, perfLog, civ, EnumUnitType.BIRD, "bird");
		build(unitList, perfLog, civ, EnumUnitType.DEAD_OR_FISH, "dead_or_fish");
		build(unitList, perfLog, civ, EnumUnitType.LIVING, "living");
		build(unitList, perfLog, civ, EnumUnitType.PROJECTILE, "projectile");
		build(unitList, perfLog, civ, EnumUnitType.BUILDING, "building");
		build(unitList, perfLog, civ, EnumUnitType.TREE, "tree");
		
		maximal = unitList.stream().mapToInt(unit -> unit.id0).max().getAsInt();
		unitList.forEach(unit -> units.put(unit.id0, unit));
	}
	
	private void build(List<OpgUnitGamedata> unitList, AssetListener perfLog, Storage civ, 
			EnumUnitType type, String uniform) throws IOException {
		unitList.addAll(Arrays.asList(
				OpgUnitFactory.get().build(type, /*civ.chdir(uniform)*/ civ, 
						p -> perfLog.initAsset(UNIT_NAME, UNIT_CLASS, Integer.parseInt(p[0])),
						p -> perfLog.readyAsset(UNIT_NAME, UNIT_CLASS, Integer.parseInt(p[0])),
						civ.chdir(uniform + ".docx").read(), 
						scribe(uniform))));
	}
	
	protected String[] scribe(String type) throws IOException {
		try(	InputStream input = getClass().getResourceAsStream("/" + type + ".tsc");	
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));	){
			return reader.lines().toArray(String[]::new);
		}
	}

	@Override
	public int max() {
		return maximal;
	}

	@Override
	public UnitGamedata query(int id) {
		return units.get(id);
	}

	@Override
	public void iterate(BiConsumer<Integer, UnitGamedata> iterator) {
		units.forEach(iterator);
	}
}
