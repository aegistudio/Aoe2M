package net.aegistudio.aoe2m.wyvern.unit;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.PriorityShaderObjects;

/**
 * Can determine the render priority of an
 * in game object.
 * 
 * @author aegistudio
 */

public interface Arbitrator {
	public void priority(PriorityShaderObjects shaderObject, 
			Terrain terrain, GraphicsInstruction instruction);
}
