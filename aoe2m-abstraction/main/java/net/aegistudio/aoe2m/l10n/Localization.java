package net.aegistudio.aoe2m.l10n;

public final class Localization {
	public static Localizer theOne = new EmptyLocalizer();
	
	public static void setInstance(Localizer instance) {
		theOne = instance;
	}
	
	public static Localizer getInstance() {
		return theOne;
	}
	
	public static String localize(String string) {
		return getInstance().localize(string);
	}
}
