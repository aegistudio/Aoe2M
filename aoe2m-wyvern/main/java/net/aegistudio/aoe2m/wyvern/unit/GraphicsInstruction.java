package net.aegistudio.aoe2m.wyvern.unit;

import java.util.Map;
import java.util.TreeMap;

/**
 * A low level render directive for rendering
 * a unit graphics.
 * 
 * @author aegistudio
 */

public class GraphicsInstruction {
	/** The sprite to render.	*/
	public GraphicsSprite sprite;
	
	/** Location for drawing the graphics. */
	public double x, y, z;
	
	/** Other basic info for a unit graphics. */
	public int frame, angle;
	
	/** Exclusive renderer hints, like player color, etc. */
	public final Map<String, Object> hint = new TreeMap<>();
}
