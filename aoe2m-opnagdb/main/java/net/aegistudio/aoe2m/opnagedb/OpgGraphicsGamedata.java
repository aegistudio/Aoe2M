package net.aegistudio.aoe2m.opnagedb;

import java.io.File;

import net.aegistudio.aoe2m.assetdba.EnumLayer;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;

public class OpgGraphicsGamedata extends GraphicsGamedata {
	public OpgGraphicsGamedata(File graphicsRoot, String[] parameters) {
		name0 = parameters[0];
		name1 = parameters[1];
		
		slp = OpgSlpImage.open(graphicsRoot, parameters[2] + ".slp");
		layer = EnumLayer.valueOf(parameters[3]);
		
		playerColor = Integer.parseInt(parameters[4]);
		adaptColor = Integer.parseInt(parameters[5]);
		
		replay = Integer.parseInt(parameters[6]);
		deltaCount = Integer.parseInt(parameters[7]);
		soundId = parameters[8];
		attackSoundUsed = Integer.parseInt(parameters[9]) == 1;
		
		frameCount = Integer.parseInt(parameters[10]);
		angleCount = Integer.parseInt(parameters[11]);
		
		frameRate = Double.parseDouble(parameters[12]);
		replayDelay = Double.parseDouble(parameters[13]);
		
		sequenceType = Integer.parseInt(parameters[14]);
		id = Integer.parseInt(parameters[15]);
		
		mirroringMode = Integer.parseInt(parameters[16]);
		graphicsDelta = parameters[17];
	}
}
