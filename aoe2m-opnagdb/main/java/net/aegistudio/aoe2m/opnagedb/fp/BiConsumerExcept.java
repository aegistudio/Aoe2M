package net.aegistudio.aoe2m.opnagedb.fp;

public interface BiConsumerExcept<E, F> {
	public void apply(E e, F f) throws Exception;	
}
