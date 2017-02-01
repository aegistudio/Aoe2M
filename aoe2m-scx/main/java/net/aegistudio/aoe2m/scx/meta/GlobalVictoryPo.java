package net.aegistudio.aoe2m.scx.meta;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.EnumContainer;
import net.aegistudio.aoe2m.Wrapper;

public class GlobalVictoryPo {
	public Wrapper<Boolean> customConquer = new Container<>(false);
	public Wrapper<Long> customMinRelic = new Container<>(0l);
	public Wrapper<Long> customExplorePercent = new Container<>(0l);
	public Wrapper<Boolean> allCustomCondition = new Container<>(false);
	
	public EnumContainer<EnumVictoryMode> victoryMode
		= new EnumContainer<>(EnumVictoryMode.STANDARD);
	
	public Wrapper<Long> requiredScore = new Container<>(0l);
	
	public Wrapper<Long> requiredTime = new Container<>(0l);
}
