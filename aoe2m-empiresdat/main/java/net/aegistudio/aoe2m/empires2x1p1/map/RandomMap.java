package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

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

	public Wrapper<Integer> baseZoneCount = new Container<>(0);
	
	public Wrapper<Integer> baseZonePointer = new Container<>(0);
	
	public Wrapper<Integer> mapTerrainCount = new Container<>(0);
	
	public Wrapper<Integer> mapTerrainPointer = new Container<>(0);
	
	public Wrapper<Integer> mapUnitCount = new Container<>(0);
	
	public Wrapper<Integer> mapUnitPointer = new Container<>(0);
	
	public Wrapper<Integer> uk1 = new Container<>(0);
	
	public Wrapper<Integer> uk2 = new Container<>(0);
	
	public void translateHeader(FieldTranslator translator) throws IOException {
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
		translator.signed32(baseZoneCount);	
		translator.signed32(baseZonePointer);
		translator.signed32(mapTerrainCount);	
		translator.signed32(mapTerrainPointer);
		translator.signed32(mapUnitCount);	
		translator.signed32(mapUnitPointer);		
		translator.signed32(uk1);	
		translator.signed32(uk2);
	}
	
	public void translateBody(FieldTranslator translator) throws IOException {
		
	}
}