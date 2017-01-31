package net.aegistudio.aoe2m.opnagedb.unit;

import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.unit.ResourceStorage;
import net.aegistudio.aoe2m.media.Storage;

public class OpgResourceStorage {
	public static final ResourceStorage NULL = new ResourceStorage(); static {
		NULL.type = -1;
		NULL.amount = 0;
		NULL.used = -1;
	};
	
	public OpgResourceStorage(Storage file) throws IOException {
		
	}
	
	public int count() {
		return 0;
	}
	
	public ResourceStorage storage(int count) {
		return NULL;
	}
}
