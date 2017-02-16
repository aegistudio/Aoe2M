package net.aegistudio.aoe2m.scx.player;

import java.util.Arrays;
import java.util.List;

import net.aegistudio.uio.wrap.BooleanContainer;
import net.aegistudio.uio.wrap.Container;
import net.aegistudio.uio.wrap.EnumContainer;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.aoe2m.scx.TextContainer;

public class PlayerData {
	// PlayerData1
	public Wrapper<String> asciiPlayerName = new Container<String>("");
	public String getAsciiPlayerName() {
		String effectiveAsciiPlayerName = asciiPlayerName.get();
		effectiveAsciiPlayerName = effectiveAsciiPlayerName.substring(
				0, effectiveAsciiPlayerName.indexOf('\0'));
		return effectiveAsciiPlayerName;
	}
	
	// version >= 1.18
	public Wrapper<Long> stringTableIndex = new Container<Long>(-1l);
	public BooleanContainer active = new BooleanContainer(false);
	public BooleanContainer human = new BooleanContainer(false);
	public EnumContainer<EnumCivilization> civilization = new EnumContainer<>(EnumCivilization.BRITONS);
	
	// PlayerData2
	public Wrapper<String> vcName = Container.string0();
	
	public Wrapper<String> ctyName = Container.string0();
	
	public static final String randomGame = "RandomGame";
	public Wrapper<String> aiName = new Container<>(randomGame);
	public Wrapper<String> perFile = Container.string0();

	public EnumContainer<EnumAiType> aiType = new EnumContainer<>(EnumAiType.STANDARD);
	
	public Wrapper<Long> initGold = new Container<Long>(0l);
	public Wrapper<Long> initWood = new Container<Long>(0l);
	public Wrapper<Long> initFood = new Container<Long>(0l);
	public Wrapper<Long> initStone = new Container<Long>(0l);
	public Wrapper<Long> initOreX = new Container<Long>(0l);
	
	// Diplomacy
	@SuppressWarnings("unchecked")
	public EnumContainer<EnumDiplomacy>[] diplomacy = new EnumContainer[16];
	{ for(int i = 0; i < 16; i ++) diplomacy[i] = new EnumContainer<EnumDiplomacy>(EnumDiplomacy.ENEMY); }
	
	public BooleanContainer alliedVictory = new BooleanContainer(false);
	
	// Disable section
	public DisableList diasabledTechs = new DisableList(30, 30);
	public DisableList diasabledUnits = new DisableList(30, 30);
	public DisableList diasabledBuildings = new DisableList(20, 40);
	
	public Wrapper<Integer> startingAge = new Container<Integer>(-1);
	
	// PlayerData4
	public Wrapper<Float> populationLimit = new Container<Float>(75f);
	
	// PlayerData3
	// Localized string Player N. Please notice it is localized.
	public TextContainer constPlayerName = new TextContainer();
	
	public Wrapper<Float> cameraX = new Container<Float>(0.0f);
	public Wrapper<Float> cameraY = new Container<Float>(0.0f);
	
	public Wrapper<Short> unknownedX = new Container<Short>((short) 72);
	public Wrapper<Short> unknownedY = new Container<Short>((short) 72);
	
	public Wrapper<Float> unknownedArrayIncluded = new Container<Float>(2.0f);	// Included when 2.0f
	
	public EnumContainer<EnumPlayerColor> playerColor = new EnumContainer<>(EnumPlayerColor.RED);
	
	public Wrapper<Short> grandTheftEmpirePlayers = new Container<Short>((short)0);
	
	// Format the player data in a readable style.
	public String toString() {
		StringBuilder toString = new StringBuilder(getClass().getCanonicalName());
		toString.append(" {");
		toString.append("\n\t");
		
		// Basis.
		toString.append("name=\"").append(getAsciiPlayerName()).append("\", ");

		toString.append("active=").append(active.get()).append(", ");
		toString.append("role=").append(human.get()? "human" : "computer").append(", ");
		toString.append("civilization=").append(civilization.get()).append(", ");
		toString.append("playerColor=").append(playerColor.get()).append(",");
		toString.append("\n\t");
		
		// Ai Config.
		toString.append("aiConfig={");
			toString.append("aiName=\"").append(aiName.get()).append("\", ");
			toString.append("perFile=\"").append(perFile.get()).append("\", ");
			toString.append(aiType.get());
		toString.append("}, ");
		toString.append("\n\t");
		
		// Resource Config.
		toString.append("resources={");
			toString.append("Food=").append(initFood.get()).append(", ");
			toString.append("Wood=").append(initWood.get()).append(", ");
			toString.append("Gold=").append(initGold.get()).append(", ");
			toString.append("Stone=").append(initStone.get()).append(", ");
			toString.append("OreX=").append(initOreX.get());
		toString.append("}, ");
		toString.append("\n\t");
		
		// Game config.
		toString.append("PopulationLimit=").append(populationLimit.get()).append(", ");
		toString.append("AlliedVictory=").append(alliedVictory.get()).append(", ");
		toString.append("StartingAge=").append(startingAge.get()).append(", ");
		toString.append("\n\t");
		
		// Diplomacy
		List<Object> dipArray = Arrays.asList(Arrays.asList(diplomacy).stream()
			.map(diplomacyEntry -> diplomacyEntry.get().toString().charAt(0)).toArray());
		toString.append("diplomacy=Diplomacy@").append(diplomacy.length)
			.append(dipArray).append(", \n\t");
		
		// Strings
		toString.append("constPlayerName=\"").append(constPlayerName.get()).append("\", ");
		toString.append("vcName=\"").append(vcName.get()).append("\", ");
		toString.append("ctyName=\"").append(ctyName.get()).append("\", ");
		toString.append("\n\t");
		
		// Camera
		toString.append("camera={");
			toString.append("x=").append(cameraX.get())
				.append("(").append(unknownedX.get()).append("), ");
			toString.append("y=").append(cameraY.get())
				.append("(").append(unknownedY.get()).append(")");
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
