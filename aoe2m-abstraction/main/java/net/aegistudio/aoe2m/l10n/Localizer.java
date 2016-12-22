package net.aegistudio.aoe2m.l10n;

/**
 * The l10n module subsume to the convention 
 * that the text will be represented in such
 * format.
 * 
 * @author aegistudio
 */

public interface Localizer {
	public String localize(String unlocalized, String... parameter);
}