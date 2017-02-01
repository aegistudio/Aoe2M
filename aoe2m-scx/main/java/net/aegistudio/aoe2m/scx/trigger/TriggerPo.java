package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Text;
import net.aegistudio.aoe2m.Wrapper;

public class TriggerPo {
	public Wrapper<Boolean> enabled = new Container<Boolean>(true);
	public Wrapper<Boolean> looping = new Container<Boolean>(false);
	public Wrapper<Byte> unknownField = new Container<Byte>((byte) 0);
	
	public Wrapper<Boolean> showObjective = new Container<Boolean>(false);
	public Wrapper<Long> objectiveOrder = new Container<Long>(0l);
	public Wrapper<Short> unknownValue = new Container<Short>((short) 0);
	
	public Wrapper<Text> triggerDescription = new Container<Text>(new Text(0, ""));	
	public Wrapper<Text> triggerName = new Container<Text>(new Text(0, ""));
	
	public OrderedList<EffectPo> effectList = new OrderedList<>(
			EffectPo::new, (effect, translator) -> effect.build(translator));
	
	public OrderedList<ConditionPo> conditionList = new OrderedList<>(
			ConditionPo::new, (condition, translator) -> condition.build(translator));
	
	public void buildTrigger(FieldTranslator translator) throws IOException, CorruptionException {
		translator.bool32(enabled);
		translator.bool32(looping);
		translator.signed8(unknownField);
		translator.bool32(showObjective);
		translator.unsigned32(objectiveOrder);
		translator.signed16(unknownValue);
		
		translator.string32(triggerDescription);
		translator.string32(triggerName);

		effectList.build(translator);
		conditionList.build(translator);
	}
}
