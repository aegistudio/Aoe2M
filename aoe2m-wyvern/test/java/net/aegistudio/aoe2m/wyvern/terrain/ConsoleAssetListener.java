package net.aegistudio.aoe2m.wyvern.terrain;

import java.io.PrintStream;
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
	public void archive(String name, Class<?> assetClass, int id) {
		output.println("Accessing archive for " + name + " #" + id);
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

	public final Map<String, PerformanceLogger> subsystems = new TreeMap<>();
	@Override
	public void initSubsystem(String name, Class<?> assetClass, int estimatedAmount) {
		PerformanceLogger logger = new PerformanceLogger(name + " manager");
		subsystems.put(name, logger);	logger.init();
	}

	@Override
	public void initAsset(String name, Class<?> assetClass, int assetId) {
		
	}

	@Override
	public void readyAsset(String name, Class<?> assetClass, int assetId) {
		
	}

	@Override
	public void readySubsystem(String name, Class<?> assetClass) {
		subsystems.get(name).ready();
	}
}
