package net.aegistudio.aoe2m.drs;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.drs.format.ArchiveHeader;
import net.aegistudio.aoe2m.drs.format.TableHeader;
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

public class TestDrsHdrRead extends ArchiveTestBase {
	public TestDrsHdrRead() {	super("gamedata.drs");	}

	public @Test void test() throws IOException, CorruptionException {
		FieldInputTranslator inputTranslator = 
				new FieldInputTranslator(url.openStream(), "utf-8");
		ArchiveHeader archive = new ArchiveHeader();
		archive.translate(inputTranslator);
		
		// Uniform header.
		ArchiveHeader archiveConst = new ArchiveHeader();
		assertEquals(archive.signature, archiveConst.signature);
		assertEquals(archive.version, archiveConst.version);
		assertEquals(archive.type, archiveConst.type);
		assertEquals(archive.tableList.size(), 1);
		assertEquals(archive.fileSectionOffset.getValue(), 0x028cl);
		
		// Table header.
		TableHeader table = archive.tableList.get(0);
		assertEquals(table.format.getValue(), "anib");
		assertEquals(table.entryOffset.getValue(), 0x004cl);
	}
}
