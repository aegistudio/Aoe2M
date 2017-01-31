package net.aegistudio.aoe2m.opnagedb.fp;

public interface BiFunctionExcept<E, F, G> { 
	public G apply(E e, F f) throws Exception;	
}
