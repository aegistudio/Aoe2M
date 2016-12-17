package net.aegistudio.aoe2m.scx.trigger;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class TriggerBuilder {
	private final OrderedList<TriggerPo> triggerList;
	public TriggerBuilder(OrderedList<TriggerPo> triggerList) {
		this.triggerList = triggerList;
	}
	
	public void buildTriggerSection(MetadataPo metadata, FieldTranslator translator) throws Exception {
		translator.constByte(0);
		triggerList.build(translator);
	}
}
