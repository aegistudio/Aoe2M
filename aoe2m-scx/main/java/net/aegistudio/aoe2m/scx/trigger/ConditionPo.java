package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.EnumWrapper;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class ConditionPo {
	public EnumWrapper<EnumConditionType> type = new EnumWrapper<>(EnumConditionType.NONE);
	public Wrapper<Integer> amount = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> resourceType = new Wrapper<Integer>(-1);
	public EnumResourceType getResourceType() { 
		return EnumResourceType.getByAuxOrder(resourceType.getValue());
	}
	
	public void setResourceType(EnumResourceType type) {
		resourceType.setValue(type.auxilliaryOrder);
	}
	
	public Wrapper<Integer> object = new Wrapper<Integer>(-1);
	public Wrapper<Integer> locate = new Wrapper<Integer>(-1);
	public Wrapper<Integer> unitType = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> player = new Wrapper<Integer>(-1);
	public Wrapper<Integer> technology = new Wrapper<Integer>(-1);
	public Wrapper<Integer> timer = new Wrapper<Integer>(-1);
	public Wrapper<Integer> unknown = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> selectLowerLeftX = new Wrapper<Integer>(-1);
	public Wrapper<Integer> selectLowerLeftY = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> selectUpperRightX = new Wrapper<Integer>(-1);
	public Wrapper<Integer> selectUpperRightY = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> unitGroup = new Wrapper<Integer>(-1);
	public Wrapper<Integer> unitType2 = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> aiSignal = new Wrapper<Integer>(-1);
	
	public void build(FieldTranslator translator) throws IOException, CorruptionException {
		translator.enum32(type);
		translator.constUnsigned32(0x10l);
		
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
}
