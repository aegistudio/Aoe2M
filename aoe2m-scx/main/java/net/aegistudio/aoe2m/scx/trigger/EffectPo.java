package net.aegistudio.aoe2m.scx.trigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.EnumContainer;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.TextContainer;
import net.aegistudio.aoe2m.scx.player.EnumDiplomacy;

public class EffectPo {
	public EnumContainer<EnumEffectType> type = new EnumContainer<>(EnumEffectType.NONE);
	public Wrapper<Integer> aiGoal = new Container<Integer>(-1);
	public Wrapper<Integer> amount = new Container<Integer>(-1);
	
	public Wrapper<Integer> resourceType = new Container<Integer>(-1);
	public EnumResourceType getResourceType() { 
		return EnumResourceType.getByAuxOrder(resourceType.getValue());
	}
	
	public void setResourceType(EnumResourceType type) {
		resourceType.setValue(type.auxilliaryOrder);
	}
	
	public Wrapper<Integer> diplomacy = new Container<Integer>(-1);
	public EnumDiplomacy getDiplomacy() { return EnumDiplomacy.getByAuxOrder(diplomacy.getValue()); }
	public void setDiplomacy(EnumDiplomacy value) { diplomacy.setValue(value.auxiliaryOrder); }
	
	public List<Wrapper<Integer>> units;
	
	public Wrapper<Integer> unitIdToLocate = new Container<Integer>(-1);
	public Wrapper<Integer> unitType = new Container<Integer>(-1);

	public Wrapper<Integer> playerSource = new Container<Integer>(-1);
	public Wrapper<Integer> playerTarget = new Container<Integer>(-1);
	
	public Wrapper<Integer> technology = new Container<Integer>(-1);
	public Wrapper<Integer> stringTableIndex = new Container<Integer>(-1);
	public Wrapper<Integer> unknown = new Container<Integer>(-1);
	public Wrapper<Integer> displayTime = new Container<Integer>(-1);
	
	public Wrapper<Integer> triggerTarget = new Container<Integer>(-1);
	
	public Wrapper<Integer> locationX = new Container<Integer>(-1);
	public Wrapper<Integer> locationY = new Container<Integer>(-1);
	
	public Wrapper<Integer> selectLowerLeftX = new Container<Integer>(-1);
	public Wrapper<Integer> selectLowerLeftY = new Container<Integer>(-1);
	
	public Wrapper<Integer> selectUpperRightX = new Container<Integer>(-1);
	public Wrapper<Integer> selectUpperRightY = new Container<Integer>(-1);
	
	public Wrapper<Integer> unitGroup = new Container<Integer>(-1);
	public Wrapper<Integer> unitType2 = new Container<Integer>(-1);
	
	public Wrapper<Integer> instructionPanel = new Container<Integer>(-1);
	public TextContainer message = new TextContainer();
	public Wrapper<String> sound = new Container<String>(new String(new byte[]{0}));	// Very special.
	
	@SuppressWarnings("unchecked")
	public void build(Translator translator) throws IOException, CorruptionException {
		translator.enum32(type.enumWrapper());
		translator.constInteger(0x17);
		
		translator.signed32(aiGoal);
		translator.signed32(amount);
		translator.signed32(resourceType);
		translator.signed32(diplomacy);
	
		Wrapper<Integer> unitsBeSelected = new Container<Integer>(
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
		translator.string32(message.stringWrapper());
		translator.string32(sound);
		
		translator.array(unitsBeSelected.getValue(), units, 
				Container::int0, translator::signed32);
	}
	
	public Wrapper<Integer> displayOrder = Container.int0();
	
	public void order(Translator translator) throws IOException {
		translator.signed32(displayOrder);
	}
}
