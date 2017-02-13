package net.aegistudio.aoe2m.empires2x1p1.unit;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class UnitCommand {
	public final Wrapper<Short> commandUsed = Container.short0();
	
	public final Wrapper<Short> id = Container.short0();
	
	public final Wrapper<Byte> uk0 = Container.byte0();
	
	public final Wrapper<Short> type = Container.short0();
	
	public final Wrapper<Short> clazz = Container.short0();
	
	public final Wrapper<Short> unit = Container.short0();
	
	public final Wrapper<Short> terrain = Container.short0();
	
	public final Wrapper<Short> resourceInput = Container.short0();
	
	public final Wrapper<Short> productivity = Container.short0();
	
	public final Wrapper<Short> resourceOutput = Container.short0();
	
	public final Wrapper<Short> resource = Container.short0();
	
	public final Wrapper<Float> quantity = Container.float0();
	
	public final Wrapper<Float> executionRadius = Container.float0();
	
	public final Wrapper<Float> extraRange = Container.float0();
	
	public final Wrapper<Byte> uk1 = Container.byte0();
	
	public final Wrapper<Float> scaringRadius = Container.float0();
	
	public final Wrapper<Byte> selectionEnabled = Container.byte0();

	public final Wrapper<Byte> uk2 = Container.byte0();

	public final Wrapper<Short> plunderSource = Container.short0();	
	
	public final Wrapper<Short> uk3 = Container.short0();

	public final Wrapper<Byte> targetsAllowed = Container.byte0();

	public final Wrapper<Byte> rightClickMode = Container.byte0();	
	
	public final Wrapper<Byte> uk4 = Container.byte0();	
	
	public final Wrapper<Short> graphicTool = Container.short0();
	
	public final Wrapper<Short> graphicProceed = Container.short0();

	public final Wrapper<Short> graphicAction = Container.short0();
	
	public final Wrapper<Short> graphicCarry = Container.short0();
	
	public final Wrapper<Short> soundExecution = Container.short0();
	
	public final Wrapper<Short> soundResourceDeposit = Container.short0();
	
	public void translate(Translator translator) throws IOException {
		translator.signed16(commandUsed);
		translator.signed16(id);
		translator.signed8(uk0);
		translator.signed16(type);
		translator.signed16(clazz);
		translator.signed16(unit);
		translator.signed16(terrain);
		translator.signed16(resourceInput);
		translator.signed16(productivity);
		translator.signed16(resourceOutput);
		translator.signed16(resource);		
		translator.float32(quantity);
		translator.float32(executionRadius);
		translator.float32(extraRange);
		translator.signed8(uk1);
		translator.float32(scaringRadius);
		translator.signed8(selectionEnabled);
		translator.signed8(uk2);
		translator.signed16(plunderSource);
		translator.signed16(uk3);
		translator.signed8(targetsAllowed);
		translator.signed8(rightClickMode);
		translator.signed8(uk4);
		
		translator.signed16(graphicTool);
		translator.signed16(graphicProceed);
		translator.signed16(graphicAction);
		translator.signed16(graphicCarry);
		translator.signed16(soundExecution);
		translator.signed16(soundResourceDeposit);
	}
}
