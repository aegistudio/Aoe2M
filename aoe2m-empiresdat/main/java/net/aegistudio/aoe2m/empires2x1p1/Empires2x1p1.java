package net.aegistudio.aoe2m.empires2x1p1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.empires2x1p1.graphics.Graphics;
import net.aegistudio.aoe2m.empires2x1p1.map.MapData;
import net.aegistudio.aoe2m.empires2x1p1.map.RandomMapData;
import net.aegistudio.aoe2m.empires2x1p1.restriction.TerrainRestriction;
import net.aegistudio.aoe2m.empires2x1p1.sound.Sound;
import net.aegistudio.aoe2m.empires2x1p1.terrain.Terrain;
import net.aegistudio.aoe2m.empires2x1p1.terrain.TerrainBlob;
import net.aegistudio.aoe2m.empires2x1p1.terrain.TerrainBorder;
import net.aegistudio.aoe2m.empires2x1p1.unit.Civilization;
import net.aegistudio.aoe2m.empires2x1p1.unit.UnitHeader;

import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class Empires2x1p1 {
	public final Wrapper<String> version = new Container<String>("");
	
	public final TerrainRestriction terrainRestriction = new TerrainRestriction();
	
	public final List<PlayerColor> playerColor = new ArrayList<>();
	
	public final List<Sound> sound = new ArrayList<>();
	
	public final Graphics graphics = new Graphics();
	
	public final MapData map = new MapData();
	
	public final List<Terrain> terrain = new ArrayList<>();
	public final Terrain emptyTerrain = new Terrain();
	
	public final List<TerrainBorder> terrainBorder = new ArrayList<>();
	
	public final TerrainBlob terrainBlob = new TerrainBlob();
	
	public final RandomMapData randomMap = new RandomMapData();
	
	public final List<Technology> technology = new ArrayList<>();
	
	public final List<UnitHeader> unitHeader = new ArrayList<>();
	
	public final List<Civilization> civilization = new ArrayList<>();
	
	public final List<Research> research = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
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
				terrainLength.getValue(), translator);
		
		Wrapper<Integer> playerColorLength = new Container<>(playerColor.size());
		translator.unsigned16(playerColorLength);
		translator.array(playerColorLength.getValue(), playerColor, 
				PlayerColor::new, wrap(translator, PlayerColor::translate));
		
		Wrapper<Integer> soundLength = new Container<>(sound.size());
		translator.unsigned16(soundLength);
		translator.array(soundLength.getValue(), sound, 
				Sound::new, wrap(translator, Sound::translate));
		
		graphics.translate(translator);
		
		map.translateMapData1(translator);
		
		translator.array(terrainLength.getValue(), terrain, Terrain::new, 
				wrap(translator, Terrain::translate));
		
		//emptyTerrain.translate(translator);
		translator.skip(438);
		
		translator.array(16, terrainBorder, TerrainBorder::new, 
				wrap(translator, TerrainBorder::translate));
		
		map.translateMapData2(translator);
		
		terrainBlob.translate(translator);
		
		randomMap.translate(translator);
		
		Wrapper<Integer> techLength = new Container<>(sound.size());
		translator.signed32(techLength);
		translator.array(techLength.getValue(), technology, 
				Technology::new, wrap(translator, Technology::translate));
		
		Wrapper<Integer> unitHeaderLength = new Container<>(unitHeader.size());
		translator.signed32(unitHeaderLength);
		translator.array(unitHeaderLength.getValue(), unitHeader, 
				UnitHeader::new, wrap(translator, UnitHeader::translate));
		
		Wrapper<Integer> civilLength = new Container<>(civilization.size());
		translator.unsigned16(civilLength);
		translator.array(civilLength.getValue(), civilization, 
				Civilization::new, wrap(translator, Civilization::translate));
		
		Wrapper<Integer> researchCount = new Container<>(research.size());
		translator.unsigned16(researchCount);
		translator.array(researchCount.getValue(), research, 
				Research::new, wrap(translator, Research::translate));
	}
}
