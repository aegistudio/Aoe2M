package net.aegistudio.aoe2m.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class FileStorage implements Storage {
	protected final File file;
	public FileStorage(File file) {
		this.file = file;
	}
	
	@Override
	public Storage chdir(String path) throws IOException {
		return new FileStorage(new File(file, path));
	}

	@Override
	public InputStream read() throws IOException {
		return new FileInputStream(file);
	}

	@Override
	public OutputStream write() throws IOException {
		return new FileOutputStream(file);
	}
	
	@Override
	public Storage[] list() throws IOException {
		return Arrays.stream(file.listFiles())
				.map(FileStorage::new).toArray(Storage[]::new);
	}

	@Override
	public Storage create(String path) throws IOException {
		File newFile = new File(file, path);
		newFile.createNewFile();
		return new FileStorage(newFile);
	}

	@Override
	public void erase() throws IOException {
		file.delete();
	}

	@Override
	public String name() {
		return file.getName();
	}
}
