package net.aegistudio.aoe2m.opnagedb.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import net.aegistudio.aoe2m.assetdba.unit.EnumAttackMode;
import net.aegistudio.aoe2m.assetdba.unit.EnumBlastAttack;
import net.aegistudio.aoe2m.assetdba.unit.EnumBlastDefence;
import net.aegistudio.aoe2m.assetdba.unit.EnumBuildingMode;
import net.aegistudio.aoe2m.assetdba.unit.EnumCreatable;
import net.aegistudio.aoe2m.assetdba.unit.EnumGarrisonType;
import net.aegistudio.aoe2m.assetdba.unit.EnumInteractionMode;
import net.aegistudio.aoe2m.assetdba.unit.EnumInteractionType;
import net.aegistudio.aoe2m.opnagedb.CsvFilter;
import net.aegistudio.aoe2m.opnagedb.FieldMapping;
import net.aegistudio.aoe2m.opnagedb.FunctionWrapper;
import net.aegistudio.uio.media.Storage;
import net.aegistudo.aoe2m.unittype.EnumUnitType;

public class OpgUnitFactory {
	protected static OpgUnitFactory instance = new OpgUnitFactory();
	public static OpgUnitFactory get() { return instance; }
	
	public final FieldMapping<OpgUnitGamedata> mapping;
	private OpgUnitFactory() {
		mapping = new FieldMapping<>(OpgUnitGamedata.class)
				/** common unit headers. **/
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
				.enumField("blast_defense_level", EnumBlastDefence.class, "blastDefence")
				.enumField("interaction_mode", EnumInteractionMode.class, "interactionMode")
				.integerField("language_dll_help", "dllHelp")
				.integerField("selection_shape", "selectionShape")
				.floatField("selection_shape_x", "selectionShapeX")
				.floatField("selection_shape_y", "selectionShapeY")
				.floatField("selection_shape_z", "selectionShapeZ")
				.map("damage_graphic", (obj, path) -> obj.damageGraphics 
						= new OpgDamageGraphics(obj.storage.open(path)))
				.integerField("sound_selection", "soundSelection")
				.integerField("sound_dying", "soundDying")
				.enumField("attack_mode", EnumAttackMode.class, "attackMode")
				.stringField("name", "name")
				.integerField("id1", "id1")
				.integerField("id2", "id2")
				
				/** flag unit. **/
				.floatField("speed", "speed")
				
				/** dead or fish unit. **/
				.integerField("walking_graphics0", "walking", "graphicsWalking0")
				.integerField("walking_graphics1", "walking", "graphicsWalking1")

				/** bird unit. **/
				.floatField("work_rate", "discover", "workRate")
				.integerField("drop_site0", "discover", "dropSite0")
				.integerField("drop_site1", "discover", "dropSite1")
				.integerField("task_swap_group_id", "discover", "taskSwapGroupId")
				.integerField("move_sound", "discover", "moveSound")
				.integerField("stop_sound", "discover", "stopSound")
				
				/** projectile unit. **/
				.enumField("interaction_type", EnumInteractionType.class, "combat", "interactionType")
				.floatField("max_range", "combat", "maxRange")
				.integerField("projectile_unit_id", "combat", "projectileUnit")
				.enumField("blast_attack_level", EnumBlastAttack.class, "combat", "blastAttackLevel")
				.integerField("attack_graphic", "combat", "graphicsAttack")
				
				/** projectile only. **/
				.floatField("projectile_arc", "projectile", "projectileArc")
				
				/** living unit. **/
				.map("resource_cost", (obj, path) -> ((OpgProductionData)obj.production).costs 
						= new OpgResourceStorage(obj.storage.open(path)))
				.integerField("creation_time", "production", "creationTime")
				.integerField("creation_location_id", "production", "creationLocationId")
				.enumField("creatable_type", EnumCreatable.class, "production", "creatableType")
				.integerField("garrison_graphic", "production", "graphicsGarrison")
				
				/** building unit. **/
				.integerField("construction_graphic_id", "building", "graphicsConstruction")
				.integerField("stack_unit_id", "building", "stackUnitId")
				.integerField("terrain_id", "building", "terrainId")
				.map("building_annex", (obj, path) -> ((OpgBuildingData)obj.building).annex
						= new OpgBuildingAnnex(obj.storage.open(path)))
				.enumField("garrison_type", EnumGarrisonType.class, "building", "garrisonType");
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
