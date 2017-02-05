package net.aegistudio.aoe2m.drs.viewer;

import net.aegistudio.aoe2m.drs.TableHeader;

public class TableViewObject {
	public final int index;
	public final TableHeader table;
	
	public TableViewObject(int index, TableHeader table) {
		this.index = index;
		this.table = table;
	}
	
	public String getFormat() {
		return new StringBuilder(table.format.getValue())
				.reverse().toString().substring(0, 3);
	}
	
	public String toString() {
		return "table #" + index + " (" + getFormat() + ")";
	}
}
