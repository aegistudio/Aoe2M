package net.aegistudio.aoe2m;

import static net.aegistudio.aoe2m.l10n.Localization.*;

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
	
	public String toString() {
		StringBuilder message = new StringBuilder(localize(super.getMessage()));
		
		if(super.getCause() != null) {
			message.append("\n\n");
			
			if(super.getCause() instanceof Aoe2mException) {
				message.append(super.getCause().toString());
			}
			else {
				message.append(super.getCause().getClass().getCanonicalName());
				message.append(": ");
				message.append(localize(super.getCause().getMessage()));
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
