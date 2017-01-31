package net.aegistudio.aoe2m.opnagedb.fp;

public interface FunctionExcept<E, F> {
	public F apply(E e) throws Exception;
}
