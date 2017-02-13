package net.aegistudio.aoe2m.drs;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.io.FieldInputTranslator;
import net.aegistudio.aoe2m.io.FieldOutputTranslator;
import net.aegistudio.aoe2m.ra.AccessInputStream;
import net.aegistudio.aoe2m.ra.AccessOutputStream;
import net.aegistudio.aoe2m.ra.RandomAccessible;

/**
 * We put archive accessing methods in the same
 * class, just like ImageIO.
 * 
 * You must provide a file or a buffer in order
 * to access the archive file.
 * 
 * @author aegistudio
 */

public final class ArchiveIO {
	public static void translate(RandomAccessible file, ArchiveHeader archive, 
			Translator translator) throws CorruptionException, IOException {
		file.seek(0l);
		archive.translate(translator);
		
		for(TableHeader table : archive.tableList) {
			file.seek(table.entryOffset.getValue());
			table.translateBody(translator);
		}
	}
	
	public static ArchiveHeader read(RandomAccessible file) throws CorruptionException, IOException {
		AccessInputStream fileStream = new AccessInputStream(file);
		Translator translator = new FieldInputTranslator(fileStream, "utf-8");
		
		ArchiveHeader result = new ArchiveHeader();
		translate(file, result, translator);
		return result;
	}
	
	public static int SAVE_PERCOUNT = 4096;	// one sector.
	public static TableEntry save(InputStream content, RandomAccessFile file) throws IOException {
		long tail = file.length();
		file.seek(tail);
		byte[] buffer = new byte[SAVE_PERCOUNT];
		while(true) {
			int current = content.read(buffer, 0, SAVE_PERCOUNT);
			if(current > 0) file.write(buffer);
			else break;
		}
		long length = file.getFilePointer() - tail;
		
		TableEntry entry = new TableEntry();
		entry.offset.setValue(tail);
		entry.length.setValue(length);
		return entry;
	}
	
	public static void write(ArchiveHeader archive, RandomAccessible file) throws CorruptionException, IOException {
		AccessOutputStream fileStream = new AccessOutputStream(file);
		Translator translator = new FieldOutputTranslator(fileStream);
		
		translate(file, archive, translator);
	}
}