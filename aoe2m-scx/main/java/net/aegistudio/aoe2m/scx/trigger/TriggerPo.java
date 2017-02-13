package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Text;
import net.aegistudio.aoe2m.TranslateWrapper;
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
	
	public List<EffectPo> effectList = new ArrayList<>();
	public List<ConditionPo> conditionList = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void build(Translator translator) throws IOException, CorruptionException {
		translator.bool32(enabled);
		translator.bool32(looping);
		translator.signed8(unknownField);
		translator.bool32(showObjective);
		translator.unsigned32(objectiveOrder);
		translator.signed16(unknownValue);
		
		translator.string32(triggerDescription);
		translator.string32(triggerName);

		Wrapper<Integer> effectSize 
			= new Container<>(effectList.size());
		translator.signed32(effectSize);
		translator.array(effectSize.getValue(), effectList, EffectPo::new, 
				TranslateWrapper.wrapAll(translator, 
						EffectPo::build, EffectPo::order));

		Wrapper<Integer> conditionSize 
			= new Container<>(conditionList.size());
		translator.signed32(conditionSize);
		translator.array(conditionSize.getValue(), conditionList, ConditionPo::new, 
				TranslateWrapper.wrapAll(translator, 
						ConditionPo::build, ConditionPo::order));
	}
	
	public Wrapper<Integer> displayOrder = Container.int0();
	
	public void order(Translator translator) throws IOException {
		translator.signed32(displayOrder);
	}
}
