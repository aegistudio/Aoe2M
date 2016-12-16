package net.aegistudio.aoe2m.scx.map;

import java.util.TreeMap;

import net.aegistudio.aoe2m.scx.Wrapper;

public class AiMapWrapper extends Wrapper<Integer>{
	public static final TreeMap<Integer, EnumAiMap> aiMapLookup;
	static {
		aiMapLookup = new TreeMap<Integer, EnumAiMap>();
	}
	
	public EnumAiMap aiMapValue;
	
	public AiMapWrapper(Integer initValue) {
		super(initValue);
		this.setValue(initValue);
	}

	public void setValue(Integer value) {
		super.setValue(value);
		aiMapValue = aiMapLookup.get(value);
	}
}
