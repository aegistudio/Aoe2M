package net.aegistudio.aoe2m.scx.map;

import java.io.IOException;

import net.aegistudio.uio.Translator;

public enum EnumIndicator {
	ERASURE {
		@Override
		public void buildUnit(UnitPo unit, Translator translator) throws IOException {
			translator.float32(unit.rotation);
			translator.signed16(unit.animationFrame);
			translator.signed32(unit.garrisonedId);
		}
	},
	UNKNOWN {
		public void buildUnit(UnitPo unit, Translator translator) throws IOException {
			translator.float32(unit.rotation);
			translator.signed16(unit.animationFrame);
			translator.signed32(unit.garrisonedId);
		}
	},
	NORMAL {
		public void buildUnit(UnitPo unit, Translator translator) throws IOException {
			translator.float32(unit.rotation);
			translator.signed16(unit.animationFrame);
			translator.signed32(unit.garrisonedId);
		}
	};
	
	public abstract void buildUnit(UnitPo unit, Translator translator) throws IOException;
	
	public static EnumIndicator getBuilder(byte ordinal) {
		if(ordinal < 0 || ordinal >= values().length)
			return ERASURE;
		return values()[ordinal];
	}
}
