package net.aegistdio.aoe2m.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.Document;
import net.aegistudio.aoe2m.impcall.ValueObserver;
import net.aegistudio.aoe2m.model.CinematicModel;
import net.aegistudio.aoe2m.model.IncludeFileModel;
import net.aegistudio.aoe2m.model.MessageModel;
import net.aegistudio.aoe2m.model.PlayerTableModel;
import net.aegistudio.aoe2m.model.TerrainModel;
import net.aegistudio.aoe2m.model.TriggerListModel;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.Wrapper;

public class Aoe2mDocument implements Document {

	// Only when updating document root objects.
	public ValueObserver<Document> self() {	return null;	}

	@Override
	public void read(InputStream inputStream) throws IOException, Aoe2mException {
		
	}

	@Override
	public void write(OutputStream outputStream) throws IOException, Aoe2mException {
		
	}

	@Override
	public void marshal(Scenario sceario) throws Aoe2mException {
		
	}

	@Override
	public Wrapper<MessageModel> message() {
		return null;
	}

	@Override
	public Wrapper<CinematicModel> cinematic() {
		return null;
	}

	@Override
	public Wrapper<PlayerTableModel> player() {
		return null;
	}

	@Override
	public Wrapper<TerrainModel> terrain() {
		return null;
	}

	@Override
	public Wrapper<TriggerListModel> trigger() {
		return null;
	}

	@Override
	public Wrapper<IncludeFileModel> includeFile() {
		return null;
	}
}
