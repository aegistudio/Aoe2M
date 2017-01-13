package net.aegistudio.aoe2m;

import net.aegistudio.aoe2m.model.CinematicModel;
import net.aegistudio.aoe2m.model.IncludeFileModel;
import net.aegistudio.aoe2m.model.MessageModel;
import net.aegistudio.aoe2m.model.ModelObject;
import net.aegistudio.aoe2m.model.PlayerTableModel;
import net.aegistudio.aoe2m.model.TerrainModel;
import net.aegistudio.aoe2m.model.TriggerListModel;
import net.aegistudio.aoe2m.scx.Scenario;

public interface Document extends ModelObject<Document> {
	public void marshal(Scenario sceario) throws Aoe2mException;
	
	public Wrapper<MessageModel> message();
	
	public Wrapper<CinematicModel> cinematic();
	
	public Wrapper<PlayerTableModel> player();
	
	public Wrapper<TerrainModel> terrain();
	
	public Wrapper<TriggerListModel> trigger();
	
	public Wrapper<IncludeFileModel> includeFile();
}