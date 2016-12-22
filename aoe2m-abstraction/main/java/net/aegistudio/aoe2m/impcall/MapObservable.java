package net.aegistudio.aoe2m.impcall;

import java.util.Collections;
import java.util.Map;

import net.aegistudio.aoe2m.Aoe2mException;

public class MapObservable<K, V> extends CollectionObservable<K, V> implements MapObserver<K, V> {
	protected final Map<K, V> map;
	public MapObservable(Map<K, V> map) {	this.map = map;		}
	public Map<K, V> map() {		return Collections.unmodifiableMap(map);		}
	
	public V put(K key, V value) {
		if(value == null) return remove(key);
		else {
			KeyPairObservable<K, V> next = null;
			if(!map.containsKey(key)) next = insert;
			
			V old = map.put(key, value);
			if(!value.equals(old)) next = replace;
			
			if(next != null) next.fire(key, value);
			return old;
		}
	}
	
	public V remove(K key) {
		boolean exists = map.containsKey(key);
		V value = map.remove(key);
		if(exists) remove.fire(key, value);
		return value;
	}
	
	public void rename(K key1, K key2) throws Aoe2mException {
		if(!map.containsKey(key1))
			throw new Aoe2mException("mapreplace.keynotexists", key1.toString());
		if(map.containsKey(key2))
			throw new Aoe2mException("mapreplace.keyexists", key2.toString());
		map.put(key2, map.remove(key1));
		
		rename.fire(key1, key2);
	}
}
