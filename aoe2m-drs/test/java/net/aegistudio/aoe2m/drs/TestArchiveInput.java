package net.aegistudio.aoe2m.drs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.ra.RandomFileAdapter;

public class TestArchiveInput extends ArchiveTestBase{
	public TestArchiveInput() {	super("gamedata.drs");	}

	public @Test void test() throws CorruptionException, IOException {
		RandomAccessFile file = new RandomAccessFile(url.getFile(), "r");
		Archive archive = Archive.open(new RandomFileAdapter(file));
		for(TableEntry entry : archive.list(0)) {
			byte[] value = archive.open(entry);
			assertEquals((long)value.length, entry.length.getValue());
		}
	}
}
