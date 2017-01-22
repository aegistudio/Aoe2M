package net.aegistudio.aoe2m.wyvern.unit;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.PriorityShaderObjects;

public class CoordinateArbitrator implements Arbitrator {
	@Override
	public void priority(PriorityShaderObjects shaderObject, 
			Terrain terrain, GraphicsInstruction instruction) {
		
		shaderObject.boundary(-terrain.height(), terrain.width());
		shaderObject.priority((float) (instruction.x - instruction.y));
	}
}
