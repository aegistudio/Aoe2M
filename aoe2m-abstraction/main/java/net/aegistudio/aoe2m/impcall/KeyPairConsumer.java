package net.aegistudio.aoe2m.impcall;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class KeyPairConsumer<K, V> implements Consumer<KeyPair<K, V>> {		
	BiConsumer<K, V> biConsumer;

	public KeyPairConsumer(BiConsumer<K, V> biConsumer) {
		this.biConsumer = biConsumer;
	}
	
	@Override
	public void accept(KeyPair<K, V> t) {
		biConsumer.accept(t.key, t.value);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if(!(obj instanceof KeyPairConsumer)) return false;
		BiConsumer objBiConsumer = ((KeyPairConsumer)obj).biConsumer;
		if(biConsumer == objBiConsumer) return true;
		return biConsumer.equals(objBiConsumer);
	}
	
	public int hashCode(){
		return biConsumer.hashCode();
	}
}

class KeyPair<K, V> {
	K key; V value;
	public KeyPair(K key, V value) {
		this.key = key;
		this.value = value;
	}
}