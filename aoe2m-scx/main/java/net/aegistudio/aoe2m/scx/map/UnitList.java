package net.aegistudio.aoe2m.scx.map;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.Wrapper;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class UnitList extends Wrapper<Long> {
	public UnitPo[] unitPo;
	
	public UnitList(Long initValue) {
		super(initValue);
		this.setValue(initValue);
	}
	
	public void setValue(Long value) {
		super.setValue(value % 0x0000ffffl);
		this.unitPo = new UnitPo[(int)(long)(this.getValue())];
		for(int i = 0; i < this.unitPo.length; i ++)
			this.unitPo[i] = new UnitPo();
	}
	
	public void buildUnitList(MetadataPo metadata, FieldTranslator translator) throws Exception {
		for(int i = 0; i < this.getValue(); i ++) {
			translator.float32(unitPo[i].positionX);
			translator.float32(unitPo[i].positionY);
			translator.float32(unitPo[i].positionHeight);
			
			translator.unsigned32(unitPo[i].unitId);
			translator.signed16(unitPo[i].unitType);
			translator.signed8(unitPo[i].indicator);
			
			UnitPo unit = unitPo[i];
			System.out.printf("- Unit#%d: %d @ (%f, %f, %f) %s\n", unit.unitId.getValue(), unit.unitType.getValue(), 
					unit.positionX.getValue(), unit.positionY.getValue(), unit.positionHeight.getValue(), unit.indicator);
			
			EnumIndicator.getBuilder(unitPo[i].indicator.getValue())
				.buildUnit(unitPo[i], metadata, translator);
		}
	}
}
