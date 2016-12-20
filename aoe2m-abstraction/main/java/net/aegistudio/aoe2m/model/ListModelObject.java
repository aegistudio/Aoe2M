package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.impcall.ListObserver;

public interface ListModelObject<V>{
	public void add(int index, V value);
	
	public V remove(int index);
	
	public void replace(int index, V value);
	
	public void reorder(int index, int another);
	
	public int count();
	
	public ListObserver<V> observer();
}
