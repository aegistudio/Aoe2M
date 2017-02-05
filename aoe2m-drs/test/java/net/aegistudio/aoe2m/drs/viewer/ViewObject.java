package net.aegistudio.aoe2m.drs.viewer;

import java.awt.Component;
import java.util.List;

public interface ViewObject {
	public class Entry {
		public String name;
		public Component component;
		
		public Entry(String name, Component component) {
			this.name = name;
			this.component = component;
		}
	}
	
	public List<Entry> openView();
}
