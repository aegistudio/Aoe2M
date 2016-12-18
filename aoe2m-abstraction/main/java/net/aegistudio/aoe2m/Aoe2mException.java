package net.aegistudio.aoe2m;

public class Aoe2mException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Create an reporting exception.
	 * Please notice the message is unlocalized and waiting
	 * to be localized.
	 */
	public Aoe2mException(String errorMessage) {
		super(errorMessage);
	}
	
	/**
	 * Create an reporting exception, with its cause.
	 * Please notice the cause will be displayed with then.
	 */
	public Aoe2mException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
}
