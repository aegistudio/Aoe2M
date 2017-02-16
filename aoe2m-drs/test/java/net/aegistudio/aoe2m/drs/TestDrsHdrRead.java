package net.aegistudio.aoe2m.drs;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.stream.InputTranslator;

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

	public @Test void test() throws IOException, CorruptException {
		InputTranslator inputTranslator = 
				new InputTranslator(url.openStream(), "utf-8");
		ArchiveHeader archive = new ArchiveHeader();
		archive.translate(inputTranslator);
		
		// Uniform header.
		ArchiveHeader archiveConst = new ArchiveHeader();
		assertEquals(archive.signature, archiveConst.signature);
		assertEquals(archive.version, archiveConst.version);
		assertEquals(archive.type, archiveConst.type);
		assertEquals(archive.tableList.size(), 1);
		assertEquals(archive.fileSectionOffset.get(), 0x028cl);
		
		// Table header.
		TableHeader table = archive.tableList.get(0);
		assertEquals(table.format.get(), "anib");
		assertEquals(table.entryOffset.get(), 0x004cl);
	}
}
