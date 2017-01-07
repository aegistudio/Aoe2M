package net.aegistudio.aoe2m.opnagedb;

public class CsvFilter {
	public static boolean filter(String line) {
		if(line.length() == 0) return false;
		if(line.startsWith("#")) return false;
		return true;
	}
	
	public static String[] map(String line) {
		return line.split(",");
	}
}
