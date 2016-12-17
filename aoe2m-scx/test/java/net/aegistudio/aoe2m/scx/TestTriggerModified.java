package net.aegistudio.aoe2m.scx;

import net.aegistudio.aoe2m.scx.trigger.ConditionPo;
import net.aegistudio.aoe2m.scx.trigger.EffectPo;
import net.aegistudio.aoe2m.scx.trigger.EnumConditionType;
import net.aegistudio.aoe2m.scx.trigger.EnumEffectType;
import net.aegistudio.aoe2m.scx.trigger.OrderedList;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;
import static org.junit.Assert.*;

public class TestTriggerModified extends TestScenarioBase {
	public TestTriggerModified() {super("/triggerModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		OrderedList<TriggerPo> list = scenario.trigger;
		assertEquals(list.size(), 1);
		
		TriggerPo trigger = list.get(0);
		assertEquals(trigger.triggerName.getValue().toString(), "TestTrigger\\0");
		assertEquals(trigger.conditionList.size(), 1);
		assertEquals(trigger.effectList.size(), 1);
		
		ConditionPo condition = trigger.conditionList.get(0);
		assertEquals(condition.type.getValue(), EnumConditionType.AI_SIGNAL);
		assertEquals(condition.aiSignal.getValue(), 0);
		
		EffectPo effect = trigger.effectList.get(0);
		assertEquals(effect.type.getValue(), EnumEffectType.SEND_CHAT);
		assertEquals(effect.message.getValue().toString(), "Test\\0");
	}
}
