package net.aegistudio.aoe2m.impcall;

import java.util.List;

public interface ListObserver<AtomType> 
	extends CollectionObserver<Integer, AtomType> {
	
	public List<AtomType> list();
}
