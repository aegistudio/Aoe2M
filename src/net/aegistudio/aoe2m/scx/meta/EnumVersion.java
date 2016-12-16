package net.aegistudio.aoe2m.scx.meta;

public enum EnumVersion {
	AGE_OF_EMPIRE_I("1.10", 1.10f, new String[]{"stone age", "tool age", "bronze age", "iron age", "post-iron age"}),
	AGE_OF_EMPIRE_II("1.18", 1.18f, new String[]{"dark age", "feudal age", "castle age", "imperial age", "post-imperial age"}),
	AGE_OF_EMPIRE_II_THE_CONQUEROR("1.21", 1.22f, new String[]{"dark age", "feudal age", "castle age", "imperial age", "post-imperial age"}),
	STAR_WARS_GALACTIC_BATTLEGROUNDS("1.21", 1.30f, new String[]{"?", "?", "?", "?", "?"});
	
	private final String versionString;
	private final float versionFloat;
	private final String[] startingAges;
	
	private EnumVersion(String versionString, float versionFloat, String[] startingAges) {
		this.versionString = versionString;
		this.versionFloat = versionFloat;
		this.startingAges = startingAges;
	}
	
	public String getVersionString() {
		return this.versionString;
	}
	
	public static EnumVersion getVersion(String versionString) {
		for(EnumVersion version : values())
			if(version.versionString.equals(versionString))
				return version;
		return null;
	}
	
	public float getVersionFloat() {
		return this.versionFloat;
	}
	
	public String getStartingAgeName(int index) {
		if(index == -1) return "none";
		return startingAges[index];
	}
}
