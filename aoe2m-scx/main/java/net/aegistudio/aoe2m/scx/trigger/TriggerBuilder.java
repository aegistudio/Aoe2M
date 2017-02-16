package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.List;

import net.aegistudio.uio.*;
import net.aegistudio.uio.wrap.*;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class TriggerBuilder {
	private final List<TriggerPo> triggerList;
	public TriggerBuilder(List<TriggerPo> triggerList) {
		this.triggerList = triggerList;
	}
	
	@SuppressWarnings("unchecked")
	public void buildTriggerSection(MetadataPo metadata, Translator translator) 
			throws IOException, CorruptException {
		
		translator.constByte((byte)0);
		Wrapper<Integer> triggerSize 
			= new Container<>(triggerList.size());
		translator.signed32(triggerSize);
		translator.array(triggerSize.get(), triggerList, TriggerPo::new, 
				TriggerPo::build, TriggerPo::order);
	}
}
