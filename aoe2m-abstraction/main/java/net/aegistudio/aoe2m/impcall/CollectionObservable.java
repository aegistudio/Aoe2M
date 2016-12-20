package net.aegistudio.aoe2m.impcall;

import java.util.function.BiConsumer;

public abstract class CollectionObservable<K, V> implements CollectionObserver<K, V> {
	protected final KeyPairObservable<K, V> insert = new KeyPairObservable<>();
	public void addInsert(BiConsumer<K, V> reactor) {		insert.add(reactor); 	}
	public void removeInsert(BiConsumer<K, V> reactor) {	insert.remove(reactor);	}

	protected final KeyPairObservable<K, V> replace = new KeyPairObservable<>();
	public void addReplace(BiConsumer<K, V> reactor) {		replace.add(reactor);		}
	public void removeReplace(BiConsumer<K, V> reactor) {	replace.remove(reactor);	}

	protected final KeyPairObservable<K, V> remove = new KeyPairObservable<>();
	public void addRemove(BiConsumer<K, V> reactor) {		remove.add(reactor);	}
	public void removeRemove(BiConsumer<K, V> reactor) {	remove.remove(reactor);	}

	protected final KeyPairObservable<K, K> rename = new KeyPairObservable<>();
	public void addRename(BiConsumer<K, K> reactor) {		rename.add(reactor);	}
	public void removeRename(BiConsumer<K, K> reactor) {	rename.remove(reactor);	}
}
