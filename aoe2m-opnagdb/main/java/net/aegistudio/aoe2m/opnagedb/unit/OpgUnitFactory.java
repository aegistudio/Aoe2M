package net.aegistudio.aoe2m.opnagedb.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import net.aegistudio.aoe2m.assetdba.unit.EnumUnitType;
import net.aegistudio.aoe2m.media.Storage;
import net.aegistudio.aoe2m.opnagedb.CsvFilter;
import net.aegistudio.aoe2m.opnagedb.FieldMapping;

public class OpgUnitFactory {
	public final FieldMapping<OpgUnitGamedata> mapping;
	public OpgUnitFactory() {
		mapping = new FieldMapping<>(OpgUnitGamedata.class)
				.integerField("id0", "id0")
				.integerField("language_dll_name", "dllName")
				.integerField("language_dll_creation", "dllCreation")
				.integerField("graphic_standing0", "graphicsStand0")
				.integerField("graphic_standing1", "graphicsStand1")
				.integerField("graphic_dying0", "graphicsDying0")
				.integerField("graphic_dying1", "graphicsDying1")
				.integerField("hit_points", "hitPoints")
				.floatField("radius_x", "radiusX")			
				.floatField("radius_y", "radiusY")
				.floatField("radius_z", "radiusZ")
				.integerField("sound_creation0", "soundCreation0")
				.integerField("sound_creation1", "soundCreation1")
				.integerField("dead_unit_id", "deadUnitId")
	//			.integerField("building_mode", "buildingMode")
				.booleanField("visible_in_fog", "visibleInFog", "1");
	}
	
	public OpgUnitGamedata[] build(EnumUnitType unitType, Storage storage, 
			Consumer<String[]> before, Consumer<String[]> after,
			InputStream inputStream, String... list) throws IOException {
		try(	InputStreamReader streamReader = new InputStreamReader(inputStream);
				BufferedReader reader = new BufferedReader(streamReader);				){
			
			return reader.lines()
					.filter(CsvFilter::filter)
					.map(CsvFilter::map)
					.map(mapping.collect(before, after, () -> {
						OpgUnitGamedata instance = new OpgUnitGamedata();
						//unitType.build(gamedata, dataBuilder);
						return instance;
						}, list))
					.toArray(OpgUnitGamedata[]::new);
		}
	}
}
