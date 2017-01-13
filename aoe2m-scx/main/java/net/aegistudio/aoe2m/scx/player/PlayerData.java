package net.aegistudio.aoe2m.scx.player;

import java.util.Arrays;
import java.util.List;

import net.aegistudio.aoe2m.EnumWrapper;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.Text;

public class PlayerData {
	// PlayerData1
	public Wrapper<String> asciiPlayerName = new Wrapper<String>("");
	public String getAsciiPlayerName() {
		String effectiveAsciiPlayerName = asciiPlayerName.getValue();
		effectiveAsciiPlayerName = effectiveAsciiPlayerName.substring(
				0, effectiveAsciiPlayerName.indexOf('\0'));
		return effectiveAsciiPlayerName;
	}
	
	// version >= 1.18
	public Wrapper<Long> stringTableIndex = new Wrapper<Long>(-1l);
	public Wrapper<Boolean> active = new Wrapper<Boolean>(false);
	public Wrapper<Boolean> human = new Wrapper<Boolean>(false);
	public EnumWrapper<EnumCivilization> civilization = new EnumWrapper<EnumCivilization>(EnumCivilization.BRITONS);
	
	// PlayerData2
	public Wrapper<Text> vcName = new Wrapper<Text>(new Text(0, ""));
	
	public Wrapper<Text> ctyName = new Wrapper<Text>(new Text(0, ""));
	
	public static final String randomGame = "RandomGame";
	public Wrapper<Text> aiName = new Wrapper<Text>(new Text(randomGame.length(), randomGame));
	public Wrapper<Text> perFile = new Wrapper<Text>(new Text(0, ""));

	public EnumWrapper<EnumAiType> aiType = new EnumWrapper<EnumAiType>(EnumAiType.STANDARD);
	
	public Wrapper<Long> initGold = new Wrapper<Long>(0l);
	public Wrapper<Long> initWood = new Wrapper<Long>(0l);
	public Wrapper<Long> initFood = new Wrapper<Long>(0l);
	public Wrapper<Long> initStone = new Wrapper<Long>(0l);
	public Wrapper<Long> initOreX = new Wrapper<Long>(0l);
	
	// Diplomacy
	@SuppressWarnings("unchecked")
	public EnumWrapper<EnumDiplomacy>[] diplomacy = new EnumWrapper[16];
	{ for(int i = 0; i < 16; i ++) diplomacy[i] = new EnumWrapper<EnumDiplomacy>(EnumDiplomacy.ENEMY); }
	
	public Wrapper<Boolean> alliedVictory = new Wrapper<Boolean>(false);
	
	// Disable section
	public DisableList diasabledTechs = new DisableList(30, 30);
	public DisableList diasabledUnits = new DisableList(30, 30);
	public DisableList diasabledBuildings = new DisableList(20, 40);
	
	public Wrapper<Integer> startingAge = new Wrapper<Integer>(-1);
	
	// PlayerData4
	public Wrapper<Float> populationLimit = new Wrapper<Float>(75f);
	
	// PlayerData3
	// Localized string Player N. Please notice it is localized.
	public Wrapper<Text> constPlayerName = new Wrapper<Text>(new Text(0, ""));
	
	public Wrapper<Float> cameraX = new Wrapper<Float>(0.0f);
	public Wrapper<Float> cameraY = new Wrapper<Float>(0.0f);
	
	public Wrapper<Short> unknownedX = new Wrapper<Short>((short) 72);
	public Wrapper<Short> unknownedY = new Wrapper<Short>((short) 72);
	
	public Wrapper<Float> unknownedArrayIncluded = new Wrapper<Float>(2.0f);	// Included when 2.0f
	
	public EnumWrapper<EnumPlayerColor> playerColor = new EnumWrapper<EnumPlayerColor>(EnumPlayerColor.RED);
	
	public Wrapper<Short> grandTheftEmpirePlayers = new Wrapper<Short>((short)0);
	
	// Format the player data in a readable style.
	public String toString() {
		StringBuilder toString = new StringBuilder(getClass().getCanonicalName());
		toString.append(" {");
		toString.append("\n\t");
		
		// Basis.
		toString.append("name=\"").append(getAsciiPlayerName()).append("\", ");

		toString.append("active=").append(active.getValue()).append(", ");
		toString.append("role=").append(human.getValue()? "human" : "computer").append(", ");
		toString.append("civilization=").append(civilization.getValue()).append(", ");
		toString.append("playerColor=").append(playerColor.getValue()).append(",");
		toString.append("\n\t");
		
		// Ai Config.
		toString.append("aiConfig={");
			toString.append("aiName=\"").append(aiName.getValue()).append("\", ");
			toString.append("perFile=\"").append(perFile.getValue()).append("\", ");
			toString.append(aiType.getValue());
		toString.append("}, ");
		toString.append("\n\t");
		
		// Resource Config.
		toString.append("resources={");
			toString.append("Food=").append(initFood.getValue()).append(", ");
			toString.append("Wood=").append(initWood.getValue()).append(", ");
			toString.append("Gold=").append(initGold.getValue()).append(", ");
			toString.append("Stone=").append(initStone.getValue()).append(", ");
			toString.append("OreX=").append(initOreX.getValue());
		toString.append("}, ");
		toString.append("\n\t");
		
		// Game config.
		toString.append("PopulationLimit=").append(populationLimit.getValue()).append(", ");
		toString.append("AlliedVictory=").append(alliedVictory.getValue()).append(", ");
		toString.append("StartingAge=").append(startingAge.getValue()).append(", ");
		toString.append("\n\t");
		
		// Diplomacy
		List<Object> dipArray = Arrays.asList(Arrays.asList(diplomacy).stream()
			.map(diplomacyEntry -> diplomacyEntry.getValue().toString().charAt(0)).toArray());
		toString.append("diplomacy=Diplomacy@").append(diplomacy.length)
			.append(dipArray).append(", \n\t");
		
		// Strings
		toString.append("constPlayerName=\"").append(constPlayerName.getValue()).append("\", ");
		toString.append("vcName=\"").append(vcName.getValue()).append("\", ");
		toString.append("ctyName=\"").append(ctyName.getValue()).append("\", ");
		toString.append("\n\t");
		
		// Camera
		toString.append("camera={");
			toString.append("x=").append(cameraX.getValue())
				.append("(").append(unknownedX.getValue()).append("), ");
			toString.append("y=").append(cameraY.getValue())
				.append("(").append(unknownedY.getValue()).append(")");
		toString.append("}, ");
		toString.append("\n\t");
		
		// Disable list.
		toString.append("disabledTechs=").append(diasabledTechs.toString()).append(", ");
		toString.append("\n\t");
		toString.append("disabledUnits=").append(diasabledUnits.toString()).append(", ");
		toString.append("\n\t");
		toString.append("disabledBuilding=").append(diasabledBuildings.toString());
		
		// End.
		toString.append("\n}");
		return new String(toString);
	}
}
