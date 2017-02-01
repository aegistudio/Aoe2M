package net.aegistudio.aoe2m.scx.meta;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.EnumWrapper;
import net.aegistudio.aoe2m.Wrapper;

public class GlobalVictoryPo {
	public Wrapper<Boolean> customConquer = new Container<Boolean>(false);
	public Wrapper<Long> customMinRelic = new Container<Long>(0l);
	public Wrapper<Long> customExplorePercent = new Container<Long>(0l);
	public Wrapper<Boolean> allCustomCondition = new Container<Boolean>(false);
	
	public EnumWrapper<EnumVictoryMode> victoryMode
		= new EnumWrapper<EnumVictoryMode>(EnumVictoryMode.STANDARD);
	
	public Wrapper<Long> requiredScore = new Container<Long>(0l);
	
	public Wrapper<Long> requiredTime = new Container<Long>(0l);
}
