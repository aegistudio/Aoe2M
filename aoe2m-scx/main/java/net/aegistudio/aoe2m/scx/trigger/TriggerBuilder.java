package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class TriggerBuilder {
	private final OrderedList<TriggerPo> triggerList;
	public TriggerBuilder(OrderedList<TriggerPo> triggerList) {
		this.triggerList = triggerList;
	}
	
	public void buildTriggerSection(MetadataPo metadata, Translator translator) 
			throws IOException, CorruptionException {
		
		translator.constByte(0);
		triggerList.build(translator);
	}
}
