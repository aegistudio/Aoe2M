package net.aegistudio.aoe2m.drs;

import java.net.URL;

import org.junit.Before;

public class ArchiveTestBase {
	private final String file;
	protected ArchiveTestBase(String file) {
		this.file = file;
	}
	
	protected URL url;
	public @Before void before() {
		url = getClass().getResource("/" + file);
		if(url == null) throw new AssertionError(
				"Please provide a " + file + " (find your AOK Data directory) in test/resources to test");
	}
}
