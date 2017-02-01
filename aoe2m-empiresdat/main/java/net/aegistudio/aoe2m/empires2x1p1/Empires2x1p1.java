package net.aegistudio.aoe2m.empires2x1p1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.empires2x1p1.graphics.Graphics;
import net.aegistudio.aoe2m.empires2x1p1.restriction.TerrainRestriction;
import net.aegistudio.aoe2m.empires2x1p1.sound.Sound;
import net.aegistudio.aoe2m.empires2x1p1.terrain.MapData;
import net.aegistudio.aoe2m.empires2x1p1.terrain.Terrain;

public class Empires2x1p1 {
	public final Wrapper<String> version = new Container<String>("");
	
	public final TerrainRestriction terrainRestriction = new TerrainRestriction();
	
	public final List<PlayerColor> playerColor = new ArrayList<>();
	
	public final List<Sound> sound = new ArrayList<>();
	
	public final Graphics graphics = new Graphics();
	
	public final MapData map = new MapData();
	
	public final List<Terrain> terrain = new ArrayList<>();
	
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.constString(8, version);
		
		Wrapper<Integer> restrictionLength 
			= new Container<>(terrainRestriction.restrictions.size());
		translator.unsigned16(restrictionLength);
		
		Wrapper<Integer> terrainLength 
			= new Container<>(terrain.size());
		translator.unsigned16(terrainLength);
		
		terrainRestriction.translate(
				restrictionLength.getValue(), 
				terrainLength.getValue(), 
				translator);
		
		Wrapper<Integer> playerColorLength = new Container<>(playerColor.size());
		translator.unsigned16(playerColorLength);
		translator.array(playerColorLength.getValue(), playerColor, 
				PlayerColor::new, item -> item.translate(translator));
		
		Wrapper<Integer> soundLength = new Container<>(sound.size());
		translator.unsigned16(soundLength);
		translator.array(soundLength.getValue(), sound, 
				Sound::new, item -> item.translate(translator));
		
		graphics.translate(translator);
		
		map.translate(translator);
		
		translator.array(terrainLength.getValue(), terrain, Terrain::new, 
				terrain -> terrain.translate(translator));
	}
}
