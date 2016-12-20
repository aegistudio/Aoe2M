package net.aegistudio.aoe2m.impcall;

import java.util.function.BiConsumer;

public interface CollectionObserver<KeyType, ValueType> {
	public void addInsert(BiConsumer<KeyType, ValueType> reactor);
	public void addReplace(BiConsumer<KeyType, ValueType> reactor);
	public void addRemove(BiConsumer<KeyType, ValueType> reactor);
	public void addRename(BiConsumer<KeyType, KeyType> reactor);
	
	public void removeInsert(BiConsumer<KeyType, ValueType> reactor);
	public void removeReplace(BiConsumer<KeyType, ValueType> reactor);
	public void removeRemove(BiConsumer<KeyType, ValueType> reactor);
	public void removeRename(BiConsumer<KeyType, KeyType> reactor);
}
