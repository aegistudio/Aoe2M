package net.aegistudio.aoe2m.opnagedb.unit;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.unit.Civilization;
import net.aegistudio.aoe2m.media.Storage;

public class OpgUnitSubsystem {
	protected final OpgUnitManager[] unitManagers;
	public OpgUnitSubsystem(AssetManager<Civilization> civ, AssetListener perfLog, Storage root) throws IOException {
		Storage gamedata = root.chdir("gamedata").chdir("gamedata-empiresdat");
		Storage civilRoot = gamedata.chdir("0000-civs");
		
		List<Civilization> civDirectories = new ArrayList<>();
		civ.iterate((id, obj) -> civDirectories.add(obj));
		int total = civDirectories.stream().mapToInt(obj -> obj.units).sum();
		
		perfLog.initSubsystem(UNIT_NAME, UNIT_CLASS, total);
		
		unitManagers = new OpgUnitManager[civ.max()];
		for(Civilization subsystem : civDirectories) {
			String temp = "0000" + subsystem.id;
			String directory = temp.substring(temp.length() - 4);
			Storage subsystemRoot = civilRoot.chdir(directory);
			
			unitManagers[subsystem.id] = new OpgUnitManager(
					subsystem.units, perfLog, subsystemRoot);
		}
		
		perfLog.readySubsystem(UNIT_NAME, UNIT_CLASS);
	}
	
	public OpgUnitManager unit(int index) {
		return unitManagers[index];
	}
}
