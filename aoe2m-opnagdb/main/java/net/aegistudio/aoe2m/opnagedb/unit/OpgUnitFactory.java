package net.aegistudio.aoe2m.opnagedb.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import net.aegistudio.aoe2m.assetdba.unit.EnumAttackMode;
import net.aegistudio.aoe2m.assetdba.unit.EnumBlastType;
import net.aegistudio.aoe2m.assetdba.unit.EnumBuildingMode;
import net.aegistudio.aoe2m.assetdba.unit.EnumInteractionMode;
import net.aegistudio.aoe2m.assetdba.unit.EnumUnitType;
import net.aegistudio.aoe2m.media.Storage;
import net.aegistudio.aoe2m.opnagedb.CsvFilter;
import net.aegistudio.aoe2m.opnagedb.FieldMapping;
import net.aegistudio.aoe2m.opnagedb.FunctionWrapper;

public class OpgUnitFactory {
	public final FieldMapping<OpgUnitGamedata> mapping;
	public OpgUnitFactory() {
		mapping = new FieldMapping<>(OpgUnitGamedata.class)
				/** Common unit headers. */
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
				.enumField("building_mode", EnumBuildingMode.class, "buildingMode")
				.booleanField("visible_in_fog", "VISIBLE", "visibleInFog")
				.integerField("fly_mode", "flyMode")
				.enumField("blast_defense_level", EnumBlastType.class, "blastDefence")
				.enumField("interaction_mode", EnumInteractionMode.class, "interactionMode")
				.integerField("language_dll_help", "dllHelp")
				.integerField("selection_shape", "selectionShape")
				.floatField("selection_shape_x", "selectionShapeX")
				.floatField("selection_shape_y", "selectionShapeY")
				.floatField("selection_shape_z", "selectionShapeZ")
				.map("damage_graphic", (obj, path) -> obj.damageGraphics 
						= new OpgDamageGraphics(obj.storage.chdir(path)))
				.integerField("sound_selection", "soundSelection")
				.integerField("sound_dying", "soundDying")
				.enumField("attack_mode", EnumAttackMode.class, "attackMode")
				.stringField("name", "name")
				.integerField("id1", "id1")
				.integerField("id2", "id2")
				
				/** Flag unit. **/
				.floatField("speed", "speed")
				
				/** Dead or fish unit. **/
				.integerField("walking_graphics0", "walking", "graphicsWalking0")
				.integerField("walking_graphics1", "walking", "graphicsWalking1")

				/** Bird or fish unit. **/
				.floatField("work_rate", "discover", "workRate")
				.integerField("drop_site0", "discover", "dropSite0")
				.integerField("drop_site1", "discover", "dropSite1")
				.integerField("task_swap_group_id", "discover", "taskSwapGroupId")
				.integerField("move_sound", "discover", "moveSound")
				.integerField("stop_sound", "discover", "stopSound");
	}
	
	public OpgUnitGamedata[] build(EnumUnitType unitType, Storage storage, 
			Consumer<String[]> before, Consumer<String[]> after,
			InputStream inputStream, String... list) throws IOException {
		try(	InputStreamReader streamReader = new InputStreamReader(inputStream);
				BufferedReader reader = new BufferedReader(streamReader);				){
			
			return reader.lines()
					.filter(CsvFilter::filter)
					.map(CsvFilter::map)
					.map(FunctionWrapper.mapIgnoreExcept(mapping.collect(before, after, 
							() -> new OpgUnitGamedata(unitType, storage), list)))
					.toArray(OpgUnitGamedata[]::new);
		}
	}
}
