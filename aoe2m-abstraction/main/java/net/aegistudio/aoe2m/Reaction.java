package net.aegistudio.aoe2m;

public interface Reaction {
	public enum Type {	ERROR, WARNING, INFORMATION	}
	
	/** Need not to be modal. */
	public void info(Type type, String text);

	/** Yes = true, No = false. */
	public boolean yesNo(Type type, String text);
	
	/** Yes = true, No = false, Cancel = null. */
	public Boolean yesNoCancel(Type type, String text);
	
	/** Retry = true, No = false, Ignore = null. */
	public Boolean retryAbortIgnore(Type type, String text);
}