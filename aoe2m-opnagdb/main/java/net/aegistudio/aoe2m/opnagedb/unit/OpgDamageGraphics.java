package net.aegistudio.aoe2m.opnagedb.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.aegistudio.aoe2m.assetdba.unit.DamageGraphics;
import net.aegistudio.aoe2m.assetdba.unit.EnumDamageApply;
import net.aegistudio.aoe2m.media.Storage;
import net.aegistudio.aoe2m.opnagedb.CsvFilter;
import net.aegistudio.aoe2m.opnagedb.FieldMapping;
import net.aegistudio.aoe2m.opnagedb.FunctionWrapper;

public class OpgDamageGraphics {
	public static final FieldMapping<DamageGraphics> mapping 
		= new FieldMapping<>(DamageGraphics.class)
			.integerField("graphic_id", "graphicsId")
			.integerField("damage_percent", "damagePercent")
			.enumField("apply_mode", "applyMode", EnumDamageApply.class);
	
	public final DamageGraphics[] damages;
	public OpgDamageGraphics(Storage file) throws IOException {
		try(	InputStreamReader input = new InputStreamReader(file.read());
				BufferedReader buffer = new BufferedReader(input);	) {
			damages = buffer.lines().filter(CsvFilter::filter)
					.map(CsvFilter::map)
					.map(FunctionWrapper.mapIgnoreExcept(
						mapping.collect(DamageGraphics::new, 
								"graphic_id", "damage_percent", "apply_mode")))
					.toArray(DamageGraphics[]::new);
		}
	}
	
	public int count() {
		return damages.length;
	}
	
	public DamageGraphics damageGraphics(int count) {
		if(count < 0 || count >= count())
			return null;
		return damages[count];
	}
}
