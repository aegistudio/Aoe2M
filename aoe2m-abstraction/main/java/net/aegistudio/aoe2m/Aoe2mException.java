package net.aegistudio.aoe2m;

import static net.aegistudio.aoe2m.l10n.Localization.*;

public class Aoe2mException extends Exception {
	private static final long serialVersionUID = 1L;
	private final String[] parameters;
	
	/**
	 * Create an reporting exception.
	 * Please notice the message is unlocalized and waiting
	 * to be localized.
	 */
	public Aoe2mException(String errorMessage, String... parameters) {
		super(errorMessage);
		this.parameters = parameters;
	}
	
	/**
	 * Create an reporting exception, with its cause.
	 * Please notice the cause will be displayed with then.
	 */
	public Aoe2mException(Throwable cause, String errorMessage, String... parameters) {
		super(errorMessage, cause);
		this.parameters = parameters;
	}
	
	public String toString() {
		StringBuilder message = new StringBuilder(localize(super.getMessage(), parameters));
		
		if(super.getCause() != null) {
			message.append("\n\n");
			
			if(super.getCause() instanceof Aoe2mException) {
				message.append(super.getCause().toString());
			}
			else {
				message.append(super.getCause().getClass().getCanonicalName());
				message.append(": ");
				message.append(super.getCause().getMessage());
				message.append("\n");
				for(StackTraceElement element : super.getCause().getStackTrace()) {
					message.append("\t");
					message.append(element.toString());
				}
			}
			
		}
		return new String(message);
	}
}
