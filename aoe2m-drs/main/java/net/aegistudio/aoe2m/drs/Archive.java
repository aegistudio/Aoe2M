package net.aegistudio.aoe2m.drs;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.ra.AccessInputStream;
import net.aegistudio.uio.ra.AccessOutputStream;
import net.aegistudio.uio.ra.RandomAccessible;

/**
 * Represents a .DRS archive file.
 * 
 * @author aegistudio
 */

public class Archive {
	protected final RandomAccessible access;
	protected final ArchiveHeader header;
	protected final AccessInputStream inputStream;
	protected final AccessOutputStream outputStream;
	
	public Archive(RandomAccessible input, ArchiveHeader header) {
		this.access = input;
		this.header = header;
		inputStream = new AccessInputStream(access);
		outputStream = new AccessOutputStream(access);
	}
	
	public static Archive open(RandomAccessible randomAccessible) 
			throws CorruptException, IOException {
		ArchiveHeader header = ArchiveIO.read(randomAccessible);
		return new Archive(randomAccessible, header);
	}
	
	public int numTables() {
		return header.tableList.size();
	}
	
	public TableHeader getTable(int tableIndex) {
		return header.tableList.get(tableIndex);
	}
	
	public List<TableEntry> list(int tableIndex) {
		return getTable(tableIndex).entries.list();
	}
	
	public TableEntry getEntry(int tableIndex, int fileIndex) {
		return getTable(tableIndex).entries.find(fileIndex);
	}
	
	public synchronized byte[] open(TableEntry entry) throws IOException {
		long length = (long)(entry.length.get());
		byte[] buffer = new byte[(int)length];
		
		synchronized(access) {
			access.seek(entry.offset.get());
			if(inputStream.read(buffer) != length)
				throw new EOFException();
		}
		return buffer;
	}
}