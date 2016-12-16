package net.aegistudio.aoe2m.scx.trigger;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class TriggerBuilder {
	public final OrderedList<TriggerPo> triggerList = new OrderedList<>(TriggerPo::new,
			(trigger, translator) -> trigger.buildTrigger(translator));
	
	public void buildTriggerSection(MetadataPo metadata, FieldTranslator translator) throws Exception {
		translator.constByte(0);
		triggerList.build(translator);
	}
}
