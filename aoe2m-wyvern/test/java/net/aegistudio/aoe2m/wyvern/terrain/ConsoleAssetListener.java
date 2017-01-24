package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import net.aegistudio.aoe2m.assetdba.AssetListener;

public class ConsoleAssetListener implements AssetListener {
	protected PrintStream output = System.err;

	class PerformanceLogger {
		public final String name;
		long timestamp = 0;	
		public PerformanceLogger(String name) {
			this.name = name;
		}
		
		public void init() {
			output.println("Initializing " + name + "...");
			timestamp = System.currentTimeMillis();			
		}
		
		public void ready() {
			output.println("Finished initialization of " + name + "... (Took " + 
					(System.currentTimeMillis() - timestamp) + " milliseconds)");			
		}
	}
	
	
	public final PerformanceLogger database = new PerformanceLogger("asset database");
	@Override
	public void initDatabase() {
		database.init();
	}

	@Override
	public void readyDatabase() {
		database.ready();
	}

	@Override
	public void initArchive(String name, Class<?> assetClass, int id) {
		output.println("Begin accessing archive for " + name + " #" + id + "...");
	}
	
	@Override
	public void readyArchive(String name, Class<?> assetClass, int id) {
		output.println("Accessing archive for " + name + " #" + id + " prepared!");
	}

	public final PerformanceLogger palette = new PerformanceLogger("player palette");
	@Override
	public void initPlayerPalette() {
		palette.init();
	}

	@Override
	public void readyPlayerPalette() {
		palette.ready();
	}

	public final Map<String, PerformanceLogger> subsystems = Collections.synchronizedMap(new TreeMap<>());
	public final Map<String, Integer> estimation = Collections.synchronizedMap(new TreeMap<>());
	@Override
	public void initSubsystem(String name, Class<?> assetClass, int estimatedAmount) {
		PerformanceLogger logger = new PerformanceLogger(name + " manager");
		subsystems.put(name, logger);	
		estimation.put(name, estimatedAmount);
		logger.init();
		output.println("We assume there'll be at most " + estimatedAmount
				+ " of " + name + " asset will be loaded.");
	}

	public final Map<String, Integer> statistics = Collections.synchronizedMap(new TreeMap<>());
	@Override
	public void initAsset(String name, Class<?> assetClass, int assetId) {
		statistics.putIfAbsent(name, 0);
	}

	int alertFrequency = 100;
	@Override
	public void readyAsset(String name, Class<?> assetClass, int assetId) {
		statistics.put(name, statistics.get(name) + 1);
		if(statistics.get(name) % alertFrequency == 0)
			output.println("We have loaded " + statistics.get(name) 
				+ "/" + estimation.get(name) + " of " + name + " asset!");
	}

	@Override
	public void readySubsystem(String name, Class<?> assetClass) {
		subsystems.get(name).ready();
	}
}
