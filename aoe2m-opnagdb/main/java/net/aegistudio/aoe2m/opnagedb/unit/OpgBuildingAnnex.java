package net.aegistudio.aoe2m.opnagedb.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.aegistudio.aoe2m.assetdba.unit.BuildingData;
import net.aegistudio.aoe2m.media.Storage;
import net.aegistudio.aoe2m.opnagedb.CsvFilter;

public class OpgBuildingAnnex {
	public final BuildingData.BuildingAnnex[] annex;
	
	public OpgBuildingAnnex(Storage file) throws IOException {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()))){
			annex = reader.lines().filter(CsvFilter::filter)
					.map(CsvFilter::map)
					.map(params -> {
						BuildingData.BuildingAnnex annexItem = new BuildingData.BuildingAnnex();
						annexItem.unit = Integer.parseInt(params[0]);
						annexItem.misplacedX = Float.parseFloat(params[1]);
						annexItem.misplacedY = Float.parseFloat(params[2]);
						return annexItem;
					})
					.filter(item -> item.unit >= 0)
					.toArray(BuildingData.BuildingAnnex[]::new);
		}
	}
	
	public int annexLength() {
		return annex.length;
	}
	
	public BuildingData.BuildingAnnex annex(int id) {
		return annex[id];
	}
} 
