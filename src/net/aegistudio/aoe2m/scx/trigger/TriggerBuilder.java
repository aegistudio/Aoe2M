package net.aegistudio.aoe2m.scx.trigger;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class TriggerBuilder {
	private TriggerListPo trigger = new TriggerListPo();
	public TriggerListPo getTriggerList() {
		return this.trigger;
	}
	
	public void buildTriggerSection(MetadataPo metadata, FieldTranslator translator) throws Exception {
		translator.constByte(0);
		
		translator.unsigned32(trigger);
		for(int i = 0; i < trigger.getValue(); i ++) 
			trigger.element[i].buildTrigger(metadata, translator);
	}
}
