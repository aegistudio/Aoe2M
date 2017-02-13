package net.aegistudio.aoe2m;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import net.aegistudio.aoe2m.Translator.ArrayTranslation;

/**
 * All entries in this list must be stored in sequential
 * manner. The entry is identified by a integer key and
 * find via binary search.
 * 
 * @author aegistudio
 */

public class SequentialList<E extends IntegerIndexed> {
	protected final List<E> elements = new ArrayList<>();
	
	public SequentialList() {  }
	public SequentialList(List<E> elements) {	addAll(elements);	}
	
	private void performSort() {
		Collections.sort(this.elements, 
				(e1, e2) -> e1.keyword() - e2.keyword());
	}
	
	private E forceEmplace(E newEntry) {
		if(elements.isEmpty()) return null;
		int existing = lookup(0, elements.size() - 1, newEntry.keyword());
		if(existing >= 0) 
			return this.elements.set(existing, newEntry);
		else return null;
	}
	
	/**
	 * If entry does not exist, place the new ones.
	 * Else replace the existing entry.
	 */
	public E emplace(E newEntry) {
		E existing = forceEmplace(newEntry);
		if(existing == null) {
			this.elements.add(newEntry);
			performSort();
		}
		return existing;
	}
	
	public void addAll(Collection<E> elements) {
		List<E> notPlaced = new ArrayList<>();
		for(E e : elements) 
			if(forceEmplace(e) == null) 
				notPlaced.add(e);
		
		this.elements.addAll(notPlaced);
		performSort();
	}
	
	private int lookup(int topIndex, int bottomIndex, int keyword) {
		if(topIndex >= bottomIndex) {
			E onlyOne = elements.get(bottomIndex);
			return onlyOne.keyword() == keyword? bottomIndex : -1;
		}
		
		int mid = (topIndex + bottomIndex) >> 1;
		E midOne = elements.get(mid);
		
		if(midOne.keyword() == keyword) return mid;
		
		return midOne.keyword() > keyword? 
				lookup(topIndex, mid - 1, keyword) : lookup(mid + 1, bottomIndex, keyword);
	}
	
	public E find(int keyword) {
		if(elements.isEmpty()) return null;
		int upperBound = elements.size() - 1;
		
		int first = elements.get(0).keyword();
		if(first > keyword) return null;
		
		int last = elements.get(upperBound).keyword();
		if(last < keyword) return null;
		
		int test = keyword - first;
		if(test < elements.size()) {
			E testing = elements.get(test);
			if(testing.keyword() == keyword)
				return testing;
		}
		
		int finalTest = lookup(0, upperBound, keyword);
		return finalTest >= 0? elements.get(finalTest) : null;
	}
	
	public int size() {
		return elements.size();
	}
	
	public List<E> list() {	
		return Collections.unmodifiableList(elements);
	}
	
	protected void validate() throws CorruptionException {
		if(elements.isEmpty()) return;
		int previous = elements.get(0).keyword();
		for(int i = 1; i < elements.size(); i ++) {
			int current = elements.get(i).keyword();
			if(current <= previous)
				throw new CorruptionException(current, previous);
			previous = current;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void entranslate(Translator translator, int length, Supplier<E> supplier, 
			ArrayTranslation<E> translation) throws IOException, CorruptionException {
		translator.array(length, elements, supplier, translation);
		validate();
	}
}
