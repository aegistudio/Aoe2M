package net.aegistudio.aoe2m.scx.player;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

@SuppressWarnings("unchecked")
public class DisableList {
	public Wrapper<Long> disabledCount = new Container<Long>(0l);
	public Wrapper<Long> disabledId[];
	public Wrapper<Long> extraDisabledId[];
	
	public DisableList(int maxDisableId, int maxExtraDisableId) {
		disabledId = new Wrapper[maxDisableId];
		for(int i = 0; i < maxDisableId; i ++) disabledId[i] = new Container<Long>(0l);
		extraDisabledId = new Wrapper[maxExtraDisableId];
		for(int i = 0; i < maxExtraDisableId; i ++) extraDisabledId[i] = new Container<Long>(0l);
	}
	
	public void buildCount(FieldTranslator translator) throws IOException {
		translator.unsigned32(disabledCount);
	}
	
	public void buildDisabledId(FieldTranslator translator) throws IOException {
		for(int i = 0; i < disabledId.length; i ++)
			translator.unsigned32(disabledId[i]);
	}
	
	public void buildExtraDisabledId(FieldTranslator translator) throws IOException {
		for(int i = 0; i < extraDisabledId.length; i ++)
			translator.unsigned32(extraDisabledId[i]);
	}
	
	public String toString() {
		StringBuilder toString = new StringBuilder("DisableList");
		toString.append("@").append(disabledCount.getValue()).append("[");
		for(int i = 0; i < disabledCount.getValue(); i ++) {
			if(i > 0) toString.append(", ");
			toString.append(disabledId[i].getValue());
		}
		toString.append("]");
		return new String(toString);
	}
}
