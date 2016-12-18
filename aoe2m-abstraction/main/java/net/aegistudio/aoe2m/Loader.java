package net.aegistudio.aoe2m;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class Loader {
	protected final String root;
	public Loader(String root) {
		this.root = root;
	}
	public void load(ClassLoader loader) throws IOException, Aoe2mException {
		URL url = loader.getResource(root);
		switch(url.getProtocol()) {
			case "file": loadFile(loader, url);	break;
			case "jar": loadJar(loader, url);	break;
			default:
				throw new Aoe2mException("loader.urlunknown\n" + url.getProtocol());
		};
	}
	
	private void loadFile(ClassLoader loader, URL url) throws IOException, Aoe2mException {
		try {
			File root = new File(url.toURI());
			if(root.isDirectory()) 
				for(File file : root.listFiles())
					load(file.getName(), loader, new FileInputStream(file));
		}
		catch(URISyntaxException uriException) {
			throw new AssertionError();
		}
	}
	
	private void loadJar(ClassLoader loader, URL url) throws IOException, Aoe2mException {
		JarURLConnection connection = (JarURLConnection) url.openConnection();
		JarFile jarFile = connection.getJarFile();
		Enumeration<JarEntry> entries = jarFile.entries();
		while(entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			if(entry.getName().startsWith(connection.getEntryName())) 
				load(entry.getName().substring(connection.getEntryName().length() + 1), 
						loader, jarFile.getInputStream(entry));
		}
	}
	
	protected abstract void load(String name, ClassLoader loader, InputStream stream) throws IOException, Aoe2mException;
}
