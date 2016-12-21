package net.aegistudio.aoe2m.core;

import net.aegistudio.aoe2m.Action;
import net.aegistudio.aoe2m.Aoe2mException;

public class PassPerformReactor implements PerformReactor {

	@Override
	public void perform(Action action) throws Aoe2mException {
		action.execute();
	}
}
