package net.aegistudio.aoe2m.scx.map;

import java.util.TreeMap;

import net.aegistudio.uio.wrap.Container;

public class AiMapWrapper extends Container<Integer>{
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
		super.set(value);
		aiMapValue = aiMapLookup.get(value);
	}
}
