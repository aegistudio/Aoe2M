package net.aegistudio.aoe2m.scx.meta;

import net.aegistudio.aoe2m.EnumWrapper;
import net.aegistudio.aoe2m.Wrapper;

public class GlobalVictoryPo {
	public Wrapper<Boolean> customConquer = new Wrapper<Boolean>(false);
	public Wrapper<Long> customMinRelic = new Wrapper<Long>(0l);
	public Wrapper<Long> customExplorePercent = new Wrapper<Long>(0l);
	public Wrapper<Boolean> allCustomCondition = new Wrapper<Boolean>(false);
	
	public EnumWrapper<EnumVictoryMode> victoryMode
		= new EnumWrapper<EnumVictoryMode>(EnumVictoryMode.STANDARD);
	
	public Wrapper<Long> requiredScore = new Wrapper<Long>(0l);
	
	public Wrapper<Long> requiredTime = new Wrapper<Long>(0l);
}
