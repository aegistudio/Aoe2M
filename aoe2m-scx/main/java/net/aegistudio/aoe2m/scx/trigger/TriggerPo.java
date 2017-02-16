package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.scx.TextContainer;
import net.aegistudio.uio.*;
import net.aegistudio.uio.wrap.*;

public class TriggerPo {
	public BooleanContainer enabled = new BooleanContainer(true);
	public BooleanContainer looping = new BooleanContainer(false);
	public Wrapper<Byte> unknownField = new Container<Byte>((byte) 0);
	
	public BooleanContainer showObjective = new BooleanContainer(false);
	public Wrapper<Long> objectiveOrder = new Container<Long>(0l);
	public Wrapper<Short> unknownValue = new Container<Short>((short) 0);
	
	public Wrapper<String> triggerDescription = Container.string0();
	public TextContainer triggerName = new TextContainer();
	
	public List<EffectPo> effectList = new ArrayList<>();
	public List<ConditionPo> conditionList = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void build(Translator translator) throws IOException, CorruptException {
		translator.signed32(enabled.bool32());
		translator.signed32(looping.bool32());
		translator.signed8(unknownField);
		translator.signed32(showObjective.bool32());
		translator.unsigned32(objectiveOrder);
		translator.signed16(unknownValue);
		
		translator.string32(triggerDescription);
		translator.string32(triggerName.stringWrapper());

		Wrapper<Integer> effectSize 
			= new Container<>(effectList.size());
		translator.signed32(effectSize);
		translator.array(effectSize.get(), effectList, EffectPo::new, 
				 EffectPo::build, EffectPo::order);

		Wrapper<Integer> conditionSize 
			= new Container<>(conditionList.size());
		translator.signed32(conditionSize);
		translator.array(conditionSize.get(), conditionList, ConditionPo::new, 
				ConditionPo::build, ConditionPo::order);
	}
	
	public Wrapper<Integer> displayOrder = Container.int0();
	
	public void order(Translator translator) throws IOException {
		translator.signed32(displayOrder);
	}
}
