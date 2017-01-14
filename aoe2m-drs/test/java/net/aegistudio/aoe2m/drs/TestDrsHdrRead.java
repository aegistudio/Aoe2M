package net.aegistudio.aoe2m.drs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.io.FieldInputTranslator;

/**
 * Only test for drs header.
 * 
 * Please notice the gamedata.drs will not be 
 * uploaded and you should provide it your own!
 * 
 * @author aegistudio
 *
 */

public class TestDrsHdrRead {
	URL url;
	public @Before void before() {
		url = getClass().getResource("/gamedata.drs");
		if(url == null) throw new AssertionError(
				"Please provide a gamedata.drs (find your AOK Data directory) in test/resources to test");
	}
	
	public @Test void test() throws IOException, CorruptionException {
		FieldInputTranslator inputTranslator = 
				new FieldInputTranslator(url.openStream(), "utf-8");
		Archive archive = new Archive();
		archive.translate(inputTranslator);
		
		// Uniform header.
		Archive archiveConst = new Archive();
		assertEquals(archive.signature, archiveConst.signature);
		assertEquals(archive.version, archiveConst.version);
		assertEquals(archive.type, archiveConst.type);
		assertEquals(archive.tableList.size(), 1);
		assertEquals(archive.fileSectionOffset.getValue(), 0x028cl);
		
		// Table header.
		Table table = archive.tableList.get(0);
		assertEquals(table.format.getValue(), "anib");
		assertEquals(table.entryOffset.getValue(), 0x004cl);
	}
}
