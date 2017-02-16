package net.aegistudio.aoe2m.scx.meta;

import net.aegistudio.uio.wrap.BooleanContainer;
import net.aegistudio.uio.wrap.Container;
import net.aegistudio.uio.wrap.EnumContainer;
import net.aegistudio.uio.Wrapper;

public class GlobalVictoryPo {
	public BooleanContainer customConquer = new BooleanContainer(false);
	public Wrapper<Long> customMinRelic = new Container<>(0l);
	public Wrapper<Long> customExplorePercent = new Container<>(0l);
	public BooleanContainer allCustomCondition = new BooleanContainer(false);
	
	public EnumContainer<EnumVictoryMode> victoryMode
		= new EnumContainer<>(EnumVictoryMode.STANDARD);
	
	public Wrapper<Long> requiredScore = new Container<>(0l);
	
	public Wrapper<Long> requiredTime = new Container<>(0l);
}
