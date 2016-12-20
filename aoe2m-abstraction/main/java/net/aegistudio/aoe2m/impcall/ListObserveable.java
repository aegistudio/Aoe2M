package net.aegistudio.aoe2m.impcall;

import java.util.Collections;
import java.util.List;

public class ListObserveable<V> extends CollectionObservable<Integer, V> implements ListObserver<V> {
	protected final List<V> list;
	public ListObserveable(List<V> list) {	this.list = list;	}
	public List<V> list() {	return Collections.unmodifiableList(list);	}

	private boolean bound(int index) {
		return index >= 0 && index < list.size();
	}
	
	public boolean add(V element) {
		list.add(element);
		super.insert.fire(list.size() - 1, element);
		return true;
	}
	
	public boolean insert(int index, V element) {
		if(!bound(index)) return false;
		
		list.add(index, element);
		super.insert.fire(index, element);
		return true;
	}
	
	public V remove(int index) {
		if(!bound(index)) return null;
		
		V value = list.remove(index);
		super.remove.fire(index, value);
		return value;
	}
	
	public V set(int index, V element) {
		if(!bound(index)) return null;
		
		V value = list.set(index, element);
		super.replace.fire(index, element);
		return value;
	}
	
	public boolean reorder(int index, int another) {
		if(!bound(index)) return false;
		if(!bound(another)) return false;
		
		list.add(another, list.remove(index));
		return true;
	}
}
