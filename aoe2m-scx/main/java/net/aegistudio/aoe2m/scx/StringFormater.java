package net.aegistudio.aoe2m.scx;

import java.util.List;

public class StringFormater {
	final StringBuilder toString;
	private boolean hasValue = false;
	public StringFormater(Object another) {	
		toString = new StringBuilder(
				another.getClass().getCanonicalName());
		toString.append(" {");
		toString.append("\n\t");
	}
	
	public void line() {
		if(hasValue) toString.append(", ");
		toString.append("\n\t");
		hasValue = false;
	}
	
	@SuppressWarnings("rawtypes")
	public void add(String key, Object value) {
		if(hasValue) toString.append(", ");
		else hasValue = true;
		
		String valueString = value.toString();;
		if(value instanceof String || value instanceof Text) 
			valueString = "\"" + valueString + "\"";
		else {
			if(value instanceof List) 
				valueString = "@"+ ((List) value).size() + valueString;
			valueString = valueString.replaceAll("\n", "\n\t");
		}
		
		toString.append(key).append("=").append(valueString);
	}
	
	public String toString() {
		return new String(toString) + "\n}";
	}
}
