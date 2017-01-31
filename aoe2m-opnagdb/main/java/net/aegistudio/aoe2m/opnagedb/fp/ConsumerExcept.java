package net.aegistudio.aoe2m.opnagedb.fp;

public interface ConsumerExcept<E> {
	public void apply(E e) throws Exception;	
}
