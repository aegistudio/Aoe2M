package net.aegistudio.aoe2m.empires2x1p1.graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class GraphicsItem {
	public final Wrapper<String> name0 = Container.string0();
	public final Wrapper<String> name1 = Container.string0();
	
	public final Wrapper<Integer> slp = Container.int1m();
	public final Wrapper<Short> uk0 = Container.short0();
	
	public final Wrapper<Byte> layer = Container.byte0();
	public final Wrapper<Short> playerColor = new Container<>((short)0);
	public final Wrapper<Byte> adaptColor = new Container<>((byte)0);
	
	public final Wrapper<Short> replay = new Container<>((short)0);
	
	public final Wrapper<Short> coordinate0 = new Container<>((short)0);
	public final Wrapper<Short> coordinate1 = new Container<>((short)0);
	public final Wrapper<Short> coordinate2 = new Container<>((short)0);
	public final Wrapper<Short> coordinate3 = new Container<>((short)0);
	public final Wrapper<Short> coordinate4 = new Container<>((short)0);
	
	public final Wrapper<Short> sound = new Container<>((short)0);
	public final Wrapper<Byte> attackSoundUsed = new Container<>((byte)0);
	public final Wrapper<Short> frameCount = new Container<>((short)0);
	public final Wrapper<Short> angleCount = new Container<>((short)0);
	
	public final Wrapper<Float> speed = new Container<>(0f);
	public final Wrapper<Float> frameRate = new Container<>(0f);
	public final Wrapper<Float> replayDelay = new Container<>(0f);
	
	public final Wrapper<Byte> sequenceType = new Container<>((byte)0);
	
	public final Wrapper<Short> id = new Container<>((short)0);
	public final Wrapper<Short> mirroringMode = new Container<>((short)0);
	public final Wrapper<Byte> uk1 = new Container<>((byte)0);	
	
	public final List<GraphicsDelta> deltas = new ArrayList<>();
	public final List<GraphicsAttackSound> attackSounds = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.string(21, name0);
		translator.string(13, name1);
		
		translator.signed32(slp);
		//translator.signed16(uk0);
		translator.signed8(layer);
		translator.signed16(playerColor);
		translator.signed8(adaptColor);
		translator.signed16(replay);
		
		translator.signed16(coordinate0);
		translator.signed16(coordinate1);
		translator.signed16(coordinate2);
		translator.signed16(coordinate3);
		
		Wrapper<Integer> deltasCount = new Container<>(deltas.size());
		translator.unsigned16(deltasCount);
		
		translator.signed16(sound);
		translator.signed8(attackSoundUsed);
		translator.signed16(frameCount);
		translator.signed16(angleCount);
		
		translator.float32(speed);
		translator.float32(frameRate);
		translator.float32(replayDelay);
		
		translator.signed8(sequenceType);
		translator.signed16(id);
		
		translator.signed16(mirroringMode);
		//translator.signed8(uk1);
		
		translator.array(deltasCount.get(), deltas, 
				GraphicsDelta::new, GraphicsDelta::translate);
		
		if(attackSoundUsed.get() != 0) 
			translator.array(angleCount.get(), attackSounds, 
					GraphicsAttackSound::new, GraphicsAttackSound::translate);
	}
}
