package net.aegistudio.aoe2m.scx.trigger;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.String32;
import net.aegistudio.aoe2m.scx.Wrapper;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class TriggerPo {
	public Wrapper<Boolean> enabled = new Wrapper<Boolean>(true);
	public Wrapper<Boolean> looping = new Wrapper<Boolean>(false);
	public Wrapper<Byte> unknownField = new Wrapper<Byte>((byte) 0);
	
	public Wrapper<Boolean> showObjective = new Wrapper<Boolean>(false);
	public Wrapper<Long> objectiveOrder = new Wrapper<Long>(0l);
	
	public Wrapper<String32> triggerDescription = new Wrapper<String32>(new String32(0, ""));	
	public Wrapper<String32> triggerName = new Wrapper<String32>(new String32(0, ""));
	
	public void buildTrigger(MetadataPo metadata, FieldTranslator translator) throws Exception {
		// Not yet completed.
		translator.bool32(enabled);
		translator.bool32(looping);
		translator.signed8(unknownField);
		translator.bool32(showObjective);
		translator.unsigned32(objectiveOrder);
		translator.constUnsigned32(0);
		
		translator.string32(triggerDescription);
		translator.string32(triggerName);
		
		
	}
}
