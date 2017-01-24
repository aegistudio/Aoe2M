package net.aegistudio.aoe2m.media;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipStorage implements Storage {
	protected final ZipFile file;
	protected final String path;
	public ZipStorage(ZipFile file, String path) {
		this.file = file;
		this.path = path;
	}
	
	@Override
	public Storage chdir(String subpath) throws IOException {
		return new ZipStorage(file, path + "/" + subpath);
	}

	private void readOnly() {	throw new AssertionError("zipStorage.readOnly");	}
	private String entryName(ZipEntry entry) { return entry.getName();	}
	private ZipStorage rebase(String path) { return new ZipStorage(file, path);	}
	
	@Override
	public Storage create(String path) throws IOException {
		readOnly();
		return null;
	}

	@Override
	public InputStream read() throws IOException {
		return file.getInputStream(file.getEntry(path));
	}
	
	@Override
	public Storage[] list() throws IOException {
		return file.stream().map(this::entryName)
			.filter(name -> name.startsWith(path + "/"))
			.map(this::rebase).toArray(Storage[]::new);
	}

	@Override
	public void erase() throws IOException {
		readOnly();
	}

	@Override
	public OutputStream write() throws IOException {
		readOnly();
		return null;
	}

	@Override
	public String name() {
		return path.substring(path.lastIndexOf('/') + 1);
	}
}
