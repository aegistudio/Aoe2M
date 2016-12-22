package net.aegistudio.aoe2m.l10n;

public class EmptyLocalizer implements Localizer {
	@Override
	public String localize(String unlocalized, String... parameter) {
		return unlocalized;
	}
}
