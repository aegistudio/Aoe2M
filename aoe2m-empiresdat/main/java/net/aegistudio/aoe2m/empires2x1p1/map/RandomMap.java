package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class RandomMap {
	public Wrapper<Integer> script = new Container<>(0);
	
	public Wrapper<Integer> borderSw = new Container<>(0);
	
	public Wrapper<Integer> borderNw = new Container<>(0);
	
	public Wrapper<Integer> borderNe = new Container<>(0);
	
	public Wrapper<Integer> borderSe = new Container<>(0);
	
	public Wrapper<Integer> borderUsage = new Container<>(0);
	
	public Wrapper<Integer> waterShape = new Container<>(0);
	
	public Wrapper<Integer> nonBaseTerrain = new Container<>(0);
	
	public Wrapper<Integer> baseZoneCoverage = new Container<>(0);
	
	public Wrapper<Integer> uk0 = new Container<>(0);

	public final List<BaseZone> baseZones = new ArrayList<>();
	private Wrapper<Integer> baseZoneCount;
	public Wrapper<Integer> baseZonePointer = new Container<>(0);

	public final List<MapTerrain> mapTerrains = new ArrayList<>();
	private Wrapper<Integer> mapTerrainCount;
	public Wrapper<Integer> mapTerrainPointer = new Container<>(0);

	public final List<MapUnit> mapUnits = new ArrayList<>();
	private Wrapper<Integer> mapUnitCount;
	public Wrapper<Integer> mapUnitPointer = new Container<>(0);

	public final List<MapUnknown> mapUnknowns = new ArrayList<>();
	private Wrapper<Integer> mapUnknownCount;
	public Wrapper<Integer> mapUnknownPointer = new Container<>(0);
	
	public void translateHeader(Translator translator) throws IOException {
		translator.signed32(script);
		translator.signed32(borderSw);
		translator.signed32(borderNw);
		translator.signed32(borderNe);
		translator.signed32(borderSe);
		translator.signed32(borderUsage);
		translator.signed32(waterShape);
		translator.signed32(nonBaseTerrain);
		translator.signed32(baseZoneCoverage);
		translator.signed32(uk0);
		translator.signed32(baseZoneCount = new Container<>(baseZones.size()));	
		translator.signed32(baseZonePointer);
		translator.signed32(mapTerrainCount = new Container<>(mapTerrains.size()));	
		translator.signed32(mapTerrainPointer);
		translator.signed32(mapUnitCount = new Container<>(mapUnits.size()));	
		translator.signed32(mapUnitPointer);		
		translator.signed32(mapUnknownCount = new Container<>(0));	
		translator.signed32(mapUnknownPointer);
	}
	
	@SuppressWarnings("unchecked")
	public void translateBody(Translator translator) throws IOException, CorruptionException {
		// Repeating headers.
		translator.constInteger(borderSw.getValue());
		translator.constInteger(borderNw.getValue());
		translator.constInteger(borderNe.getValue());
		translator.constInteger(borderSe.getValue());
		translator.constInteger(borderUsage.getValue());
		translator.constInteger(waterShape.getValue());
		translator.constInteger(nonBaseTerrain.getValue());
		translator.constInteger(baseZoneCoverage.getValue());
		translator.constInteger(uk0.getValue());
		
		// Base zone.
		translator.constInteger(baseZoneCount.getValue());
		translator.constInteger(baseZonePointer.getValue());
		translator.array(baseZoneCount.getValue(), baseZones, BaseZone::new, 
				wrap(translator, BaseZone::translate));
		
		// Map terrain.
		translator.constInteger(mapTerrainCount.getValue());
		translator.constInteger(mapTerrainPointer.getValue());
		translator.array(mapTerrainCount.getValue(), mapTerrains, MapTerrain::new, 
				wrap(translator, MapTerrain::translate));
		
		// Map units.
		translator.constInteger(mapUnitCount.getValue());
		translator.constInteger(mapUnitPointer.getValue());
		translator.array(mapUnitCount.getValue(), mapUnits, MapUnit::new, 
				wrap(translator, MapUnit::translate));
		
		// Map unknowns.
		translator.constInteger(mapUnknownCount.getValue());
		translator.constInteger(mapUnknownPointer.getValue());
		translator.array(mapUnknownCount.getValue(), mapUnknowns, MapUnknown::new, 
				wrap(translator, MapUnknown::translate));
	}
}