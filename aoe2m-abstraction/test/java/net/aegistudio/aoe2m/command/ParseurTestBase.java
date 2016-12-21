package net.aegistudio.aoe2m.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.aegistudio.aoe2m.Aoe2mException;

public abstract class ParseurTestBase {
	protected CommandParseur parseur;
	public @Before void before() {	
		parseur = get();
	}
	
	protected abstract CommandParseur get();
	
	private void testReconstruct(String... parameters) throws Aoe2mException  {
		if(parameters.length == 0) return;
		
		StringBuilder concat = new StringBuilder(parameters[0]);
		for(int i = 1; i < parameters.length; i ++) {
			concat.append(" ");
			concat.append(parseur.encode(parameters[i]));
		}
		assertEquals(parameters, parseur.parse(concat.toString()));
	}
	
	public @Test void testSimple() throws Aoe2mException {
		testReconstruct("simple");
	}
	
	public @Test void testSpace() throws Aoe2mException {
		testReconstruct("space", "space on");
	}
	
	public @Test void testBreak() throws Aoe2mException {
		testReconstruct("break", "break\non");
	}
	
	public @Test void testQuote() throws Aoe2mException {
		testReconstruct("quote", "\"quote\'on");
	}
	
	public @Test void testSlash() throws Aoe2mException {
		testReconstruct("slash", "\\slash\\on");
	}
}
