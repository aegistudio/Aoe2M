package net.aegistudio.aoe2m.impcall.l10n;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Locale;

import net.aegistudio.aoe2m.l10n.ConfigLocalizer;

public class TestConfigLocalizer {
	static ConfigLocalizer config;
	public static @BeforeClass void setupClass() {
		try {
			config = new ConfigLocalizer();
			config.load(ClassLoader.getSystemClassLoader());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public @After void tear() {
		config.locale = Locale.getDefault();
	}
	
	public @Test void testSingle() {
		assertEquals(config.localize("test.single"), "SingleResult");
	}
	
	public @Test void testParameter() {
		String randomString1 = Long.toHexString(System.currentTimeMillis());
		String randomString2 = Long.toHexString(
				(long) (Math.random() *  System.currentTimeMillis()));
		assertEquals(config.localize("test.parameter", randomString1, randomString2), 
				randomString2 + randomString1);
	}
	
	public @Test void testSingleChinese() {
		config.locale = new Locale("zh");
		assertEquals(config.localize("test.single"), "简体结果");
	}
	
	public @Test void testFallbackChinese() {
		config.locale = new Locale("zh");
		testParameter();
	}
	
	public @Test void testAbscence() {
		assertEquals(config.localize("test.abscence"), "test.abscence");
	}
}
