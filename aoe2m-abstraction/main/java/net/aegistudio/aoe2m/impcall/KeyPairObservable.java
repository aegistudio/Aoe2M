package net.aegistudio.aoe2m.impcall;

import java.util.function.BiConsumer;

public class KeyPairObservable<K, V> extends Observable<KeyPair<K, V>> {
	public void add(BiConsumer<K, V> bic) {
		add(new KeyPairConsumer<>(bic));
	}
	
	public void remove(BiConsumer<K, V> bic) {
		remove(new KeyPairConsumer<>(bic));
	}
	
	public void fire(K key, V value) {
		fire(new KeyPair<>(key, value));
	}
}
