package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.EnumWrapper;
import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.String32;
import net.aegistudio.aoe2m.scx.Wrapper;
import net.aegistudio.aoe2m.scx.player.EnumDiplomacy;

public class EffectPo {
	public EnumWrapper<EnumEffectType> type = new EnumWrapper<>(EnumEffectType.NONE);
	public Wrapper<Integer> aiGoal = new Wrapper<Integer>(-1);
	public Wrapper<Integer> amount = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> resourceType = new Wrapper<Integer>(-1);
	public EnumResourceType getResourceType() { 
		return EnumResourceType.getByAuxOrder(resourceType.getValue());
	}
	
	public void setResourceType(EnumResourceType type) {
		resourceType.setValue(type.auxilliaryOrder);
	}
	
	public Wrapper<Integer> diplomacy = new Wrapper<Integer>(-1);
	public EnumDiplomacy getDiplomacy() { return EnumDiplomacy.getByAuxOrder(diplomacy.getValue()); }
	public void setDiplomacy(EnumDiplomacy value) { diplomacy.setValue(value.auxiliaryOrder); }
	
	public List<Wrapper<Integer>> units;
	
	public Wrapper<Integer> unitIdToLocate = new Wrapper<Integer>(-1);
	public Wrapper<Integer> unitType = new Wrapper<Integer>(-1);

	public Wrapper<Integer> playerSource = new Wrapper<Integer>(-1);
	public Wrapper<Integer> playerTarget = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> technology = new Wrapper<Integer>(-1);
	public Wrapper<Integer> stringTableIndex = new Wrapper<Integer>(-1);
	public Wrapper<Integer> unknown = new Wrapper<Integer>(-1);
	public Wrapper<Integer> displayTime = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> triggerTarget = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> locationX = new Wrapper<Integer>(-1);
	public Wrapper<Integer> locationY = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> selectLowerLeftX = new Wrapper<Integer>(-1);
	public Wrapper<Integer> selectLowerLeftY = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> selectUpperRightX = new Wrapper<Integer>(-1);
	public Wrapper<Integer> selectUpperRightY = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> unitGroup = new Wrapper<Integer>(-1);
	public Wrapper<Integer> unitType2 = new Wrapper<Integer>(-1);
	
	public Wrapper<Integer> instructionPanel = new Wrapper<Integer>(-1);
	public Wrapper<String32> message = new Wrapper<String32>(new String32(0, ""));
	public Wrapper<String32> sound = new Wrapper<String32>(new String32(1, new String(new byte[]{0})));	// Very special.
	
	public void build(FieldTranslator translator) throws IOException, CorruptionException {
		translator.enum32(type);
		translator.constUnsigned32(0x17);
		
		translator.signed32(aiGoal);
		translator.signed32(amount);
		translator.signed32(resourceType);
		translator.signed32(diplomacy);
	
		Wrapper<Integer> unitsBeSelected = new Wrapper<Integer>(
				units != null? units.size() : -1);
		translator.signed32(unitsBeSelected);
		if(unitsBeSelected.getValue() >= 0 && units == null)
			units = new ArrayList<Wrapper<Integer>>();
		
		translator.signed32(unitIdToLocate);
		translator.signed32(unitType);
		
		translator.signed32(playerSource);
		translator.signed32(playerTarget);
		
		translator.signed32(technology);
		translator.signed32(stringTableIndex);
		
		translator.signed32(unknown);
		translator.signed32(displayTime);
		
		translator.signed32(triggerTarget);
		
		translator.signed32(locationX);	translator.signed32(locationY);
		translator.signed32(selectLowerLeftX); translator.signed32(selectLowerLeftY);
		translator.signed32(selectUpperRightX); translator.signed32(selectUpperRightY);
		
		translator.signed32(unitGroup);
		translator.signed32(unitType2);
		
		translator.signed32(instructionPanel);
		translator.string32(message);
		translator.string32(sound);
		
		translator.array(unitsBeSelected.getValue(), units, 
				() -> new Wrapper<Integer>(0), 
				unit -> translator.signed32(unit));
	}
}