package net.aegistudio.aoe2m.empires2x1p1.terrain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class Terrain {
	public Wrapper<Short> uk0 = new Container<>((short)0);
	
	public Wrapper<Short> uk1 = new Container<>((short)0);
	
	public Wrapper<String> name0 = new Container<>("");
	
	public Wrapper<String> name1 = new Container<>("");
	
	public Wrapper<Integer> slp = new Container<>(0);
	
	public Wrapper<Integer> uk2 = new Container<>(0);
	
	public Wrapper<Integer> sound = new Container<>(0);
	
	public Wrapper<Integer> blendPriority = new Container<>(0);	
	
	public Wrapper<Integer> blendMode = new Container<>(0);
	
	public Wrapper<Byte> colorHi = new Container<>((byte)0);
	
	public Wrapper<Byte> colorMed = new Container<>((byte)0);
	
	public Wrapper<Byte> colorLo = new Container<>((byte)0);
	
	public Wrapper<Byte> colorCliffLt = new Container<>((byte)0);
	
	public Wrapper<Byte> colorCliffRt = new Container<>((byte)0);

	public Wrapper<Byte> passable = new Container<>((byte)-1);
	
	public Wrapper<Byte> impassable = new Container<>((byte)-1);
	
	public Wrapper<Byte> uk3 = new Container<>((byte)0);
	
	public Wrapper<Float> uk4 = new Container<>(0f);
	
	public Wrapper<String> uk5 = new Container<>("");
	
	public Wrapper<Short> frameCount = new Container<>((short)0);
	
	public Wrapper<Short> angleCount = new Container<>((short)0);
	
	public Wrapper<Short> id = new Container<>((short)0);
	
	public List<Wrapper<Short>> elevationGraphics = new ArrayList<>();
	
	public Wrapper<Short> replacement = new Container<>((short)0);
	
	public Wrapper<Short> dimension0 = new Container<>((short)0);
	
	public Wrapper<Short> dimension1 = new Container<>((short)0);
	
	public List<Wrapper<Byte>> borders = new ArrayList<>();

	public List<Wrapper<Short>> units = new ArrayList<>();
	
	public List<Wrapper<Short>> densities = new ArrayList<>();
	
	public List<Wrapper<Byte>> placementFlags = new ArrayList<>();
	
	public Wrapper<Short> unitsUsed = new Container<>((short)0);
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.signed16(uk0);
		translator.signed16(uk1);

		translator.string(13, name0);
		translator.string(13, name1);
		
		translator.signed32(slp);
		translator.signed32(uk2);
		
		translator.signed32(sound);
		translator.signed32(blendPriority);
		translator.signed32(blendMode);
		
		translator.signed8(colorHi);
		translator.signed8(colorMed);
		translator.signed8(colorLo);
		translator.signed8(colorCliffLt);
		translator.signed8(colorCliffRt);
		translator.signed8(passable);
		translator.signed8(impassable);
		translator.signed8(uk3);
		
		translator.float32(uk4);
		translator.string(18, uk5);
		
		translator.signed16(frameCount);
		translator.signed16(angleCount);
		
		translator.signed16(id);
		translator.array(54, elevationGraphics, 
				Container::short0, 
				Translator.reverse(Translator::signed16));
		translator.signed16(replacement);	
		translator.signed16(dimension0);
		translator.signed16(dimension1);
		
		translator.array(84, borders, 
				Container::byte0, 
				Translator.reverse(Translator::signed8));
		
		translator.array(30, units, 
				Container::short0, 
				Translator.reverse(Translator::signed16));

		translator.array(30, densities, 
				Container::short0, 
				Translator.reverse(Translator::signed16));
		
		translator.array(30, placementFlags, 
				Container::byte0, 
				Translator.reverse(Translator::signed8));
		
		translator.signed16(unitsUsed);
	}
}

