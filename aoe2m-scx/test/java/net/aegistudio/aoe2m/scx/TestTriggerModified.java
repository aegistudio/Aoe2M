package net.aegistudio.aoe2m.scx;

import net.aegistudio.aoe2m.scx.trigger.ConditionPo;
import net.aegistudio.aoe2m.scx.trigger.EffectPo;
import net.aegistudio.aoe2m.scx.trigger.EnumConditionType;
import net.aegistudio.aoe2m.scx.trigger.EnumEffectType;
import net.aegistudio.aoe2m.scx.trigger.TriggerPo;
import static org.junit.Assert.*;

import java.util.List;

public class TestTriggerModified extends ScenarioReadBase {
	public TestTriggerModified() {super("/triggerModified.scx", "gbk"); }
	
	public void todo(Scenario scenario) {
		List<TriggerPo> list = scenario.trigger;
		assertEquals(list.size(), 1);
		
		TriggerPo trigger = list.get(0);
		assertEquals(trigger.triggerName.get().string, "TestTrigger\0");
		assertEquals(trigger.conditionList.size(), 1);
		assertEquals(trigger.effectList.size(), 1);
		
		ConditionPo condition = trigger.conditionList.get(0);
		assertEquals(condition.type.get(), EnumConditionType.AI_SIGNAL);
		assertEquals(condition.aiSignal.get(), 0);
		
		EffectPo effect = trigger.effectList.get(0);
		assertEquals(effect.type.get(), EnumEffectType.SEND_CHAT);
		assertEquals(effect.message.get().string, "Test\0");
	}
}
