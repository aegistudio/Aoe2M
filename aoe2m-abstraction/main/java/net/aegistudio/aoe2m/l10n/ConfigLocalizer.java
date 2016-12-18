package net.aegistudio.aoe2m.l10n;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.Loader;

public class ConfigLocalizer extends Loader implements Localizer {
	public ConfigLocalizer() {	super("locale");	}
	public Locale locale = Locale.getDefault();
	
	public TreeMap<String, LocalizeEntry> entry = new TreeMap<>(); class LocalizeEntry {
		Map<Locale, String> locale = new HashMap<>();
		String fallback = null;
		
		public void set(Locale locale, String value) {
			if(locale == null) fallback = value;
			else this.locale.put(locale, value);
		}
		
		public String get() {
			return locale.getOrDefault(locale, fallback);
		}
	}
	
	@Override
	public String localize(String unlocalized) {
		String[] entries = unlocalized.split("[\n\r]+");
		String semiLocale = entries[0];
		
		LocalizeEntry entry = this.entry.get(semiLocale);
		if(entry != null) semiLocale = entry.get();
		
		for(int i = 1; i < entries.length; i ++)
			semiLocale = semiLocale.replace("$" + i, entries[i]);
		return semiLocale;
	}
	
	public void put(Locale locale, String key, String value) {
		LocalizeEntry newly = entry.get(key);
		if(newly == null) {
			newly = new LocalizeEntry();
			entry.put(key, newly);
		}
		newly.set(locale, value);
	}

	@Override
	protected void load(String name, ClassLoader loader, InputStream stream) throws Aoe2mException, IOException {
		if(!name.endsWith(".properties")) return;
		name = name.substring(0, name.length() - ".properties".length());
		Locale localeInput = null;
		if(name.lastIndexOf('.') >= 0) localeInput = new Locale(
				name.substring(name.lastIndexOf('.') + 1));
		final Locale locale = localeInput;
		
		Properties properties = new Properties();
		properties.load(stream);
		properties.forEach((k, v) -> put(locale, k.toString(), v.toString()));
	}
}
