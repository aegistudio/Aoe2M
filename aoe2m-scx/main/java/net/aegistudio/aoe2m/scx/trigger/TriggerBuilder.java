package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.TranslateWrapper;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class TriggerBuilder {
	private final List<TriggerPo> triggerList;
	public TriggerBuilder(List<TriggerPo> triggerList) {
		this.triggerList = triggerList;
	}
	
	@SuppressWarnings("unchecked")
	public void buildTriggerSection(MetadataPo metadata, Translator translator) 
			throws IOException, CorruptionException {
		
		translator.constByte(0);
		Wrapper<Integer> triggerSize 
			= new Container<>(triggerList.size());
		translator.signed32(triggerSize);
		translator.array(triggerSize.getValue(), triggerList, TriggerPo::new, 
				TranslateWrapper.wrapAll(translator, 
						TriggerPo::build, TriggerPo::order));
	}
}
