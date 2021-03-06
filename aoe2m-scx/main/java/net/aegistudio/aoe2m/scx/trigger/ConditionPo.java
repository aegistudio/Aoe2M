package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;

import net.aegistudio.uio.wrap.*;
import net.aegistudio.uio.*;

public class ConditionPo {
	public EnumContainer<EnumConditionType> type = new EnumContainer<>(EnumConditionType.NONE);
	public Wrapper<Integer> amount = new Container<Integer>(-1);
	
	public Wrapper<Integer> resourceType = new Container<Integer>(-1);
	public EnumResourceType getResourceType() { 
		return EnumResourceType.getByAuxOrder(resourceType.get());
	}
	
	public void setResourceType(EnumResourceType type) {
		resourceType.set(type.auxilliaryOrder);
	}
	
	public Wrapper<Integer> object = new Container<Integer>(-1);
	public Wrapper<Integer> locate = new Container<Integer>(-1);
	public Wrapper<Integer> unitType = new Container<Integer>(-1);
	
	public Wrapper<Integer> player = new Container<Integer>(-1);
	public Wrapper<Integer> technology = new Container<Integer>(-1);
	public Wrapper<Integer> timer = new Container<Integer>(-1);
	public Wrapper<Integer> unknown = new Container<Integer>(-1);
	
	public Wrapper<Integer> selectLowerLeftX = new Container<Integer>(-1);
	public Wrapper<Integer> selectLowerLeftY = new Container<Integer>(-1);
	
	public Wrapper<Integer> selectUpperRightX = new Container<Integer>(-1);
	public Wrapper<Integer> selectUpperRightY = new Container<Integer>(-1);
	
	public Wrapper<Integer> unitGroup = new Container<Integer>(-1);
	public Wrapper<Integer> unitType2 = new Container<Integer>(-1);
	
	public Wrapper<Integer> aiSignal = new Container<Integer>(-1);
	
	public void build(Translator translator) throws IOException, CorruptException {
		translator.signed32(type.enum32());
		translator.constInteger(0x10);
		
		translator.signed32(amount);
		translator.signed32(resourceType);
		
		translator.signed32(object);
		translator.signed32(locate);
		translator.signed32(unitType);
		
		translator.signed32(player);
		translator.signed32(technology);
		translator.signed32(timer);
		translator.signed32(unknown);
		
		translator.signed32(selectLowerLeftX); translator.signed32(selectLowerLeftY);
		translator.signed32(selectUpperRightX); translator.signed32(selectUpperRightY);
		
		translator.signed32(unitGroup);
		translator.signed32(unitType2);
		
		translator.signed32(aiSignal);
	}
	
	public Wrapper<Integer> displayOrder = Container.int0();
	
	public void order(Translator translator) throws IOException {
		translator.signed32(displayOrder);
	}
}
