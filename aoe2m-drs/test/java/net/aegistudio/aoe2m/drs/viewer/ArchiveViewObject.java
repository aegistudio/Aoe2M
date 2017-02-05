package net.aegistudio.aoe2m.drs.viewer;

import java.io.File;

import net.aegistudio.aoe2m.drs.Archive;

public class ArchiveViewObject {
	public final File file;
	public final Archive archive;
	public ArchiveViewObject(File file, Archive archive) {
		this.archive = archive;
		this.file = file;
	}
	
	public String toString() {
		return this.file.getName();
	}
}
