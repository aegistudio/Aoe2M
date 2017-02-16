package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

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
	public void translateBody(Translator translator) throws IOException, CorruptException {
		// Repeating headers.
		translator.constInteger(borderSw.get());
		translator.constInteger(borderNw.get());
		translator.constInteger(borderNe.get());
		translator.constInteger(borderSe.get());
		translator.constInteger(borderUsage.get());
		translator.constInteger(waterShape.get());
		translator.constInteger(nonBaseTerrain.get());
		translator.constInteger(baseZoneCoverage.get());
		translator.constInteger(uk0.get());
		
		// Base zone.
		translator.constInteger(baseZoneCount.get());
		translator.constInteger(baseZonePointer.get());
		translator.array(baseZoneCount.get(), baseZones, BaseZone::new, 
				BaseZone::translate);
		
		// Map terrain.
		translator.constInteger(mapTerrainCount.get());
		translator.constInteger(mapTerrainPointer.get());
		translator.array(mapTerrainCount.get(), mapTerrains, MapTerrain::new, 
				MapTerrain::translate);
		
		// Map units.
		translator.constInteger(mapUnitCount.get());
		translator.constInteger(mapUnitPointer.get());
		translator.array(mapUnitCount.get(), mapUnits, MapUnit::new, 
				MapUnit::translate);
		
		// Map unknowns.
		translator.constInteger(mapUnknownCount.get());
		translator.constInteger(mapUnknownPointer.get());
		translator.array(mapUnknownCount.get(), mapUnknowns, MapUnknown::new, 
				MapUnknown::translate);
	}
}