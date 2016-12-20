package net.aegistudio.aoe2m.impcall;

import java.util.Map;

public interface MapObserver<KeyType, ValueType> 
		extends CollectionObserver<KeyType, ValueType> {
	
	public Map<KeyType, ValueType> map();
}