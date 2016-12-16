package net.aegistudio.aoe2m.scx.trigger;

public class TriggerListPo extends OrderedList<TriggerPo> {
	@Override
	public TriggerPo[] list(int length) {
		return new TriggerPo[length];
	}

	@Override
	public TriggerPo newInstance() {
		return new TriggerPo();
	}
}
