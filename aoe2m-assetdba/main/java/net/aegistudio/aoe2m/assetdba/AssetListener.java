package net.aegistudio.aoe2m.assetdba;

/**
 * A performance logger for higher level
 * components to know current status of 
 * the asset database.
 * 
 * Don't see such class as a verifier of
 * system status. The actual implementation
 * should provide interface for determining
 * whether performance logging is allowed.
 * 
 * @author aegistudio
 */

public interface AssetListener {
	public void initDatabase();
	
	public void readyDatabase();
	
	public void archive(String name, Class<?> assetClass, int id);
	
	public void initPlayerPalette();
	
	public void readyPlayerPalette();
	
	public void initSubsystem(String name, Class<?> assetClass, int estimatedAmount);
	
	public void initAsset(String name, Class<?> assetClass, int assetId);
	
	public void readyAsset(String name, Class<?> assetClass, int assetId);
	
	public void readySubsystem(String name, Class<?> assetClass);
}
