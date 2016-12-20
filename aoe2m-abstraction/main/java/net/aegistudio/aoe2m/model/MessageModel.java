package net.aegistudio.aoe2m.model;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.msg.Message;

public interface MessageModel extends ModelObject<MessageModel> {
	public void marshal(Scenario sceario, Message message) 
			throws Aoe2mException;
}
