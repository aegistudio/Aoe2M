package net.aegistudio.aoe2m.opnagedb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.EnumLayer;
import net.aegistudio.aoe2m.assetdba.GraphicsDelta;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;

public class OpgGraphicsGamedata extends GraphicsGamedata {
	public OpgGraphicsGamedata(File graphicsRoot, 
			File graphicsDeltaRoot, String[] parameters) throws IOException {
		
		name0 = parameters[0];
		name1 = parameters[1];
		
		slp = OpgSlpImage.open(graphicsRoot, parameters[2] + ".slp");
		layer = EnumLayer.valueOf(parameters[3]);
		
		playerColor = Integer.parseInt(parameters[4]);
		adaptColor = Integer.parseInt(parameters[5]);
		
		replay = Integer.parseInt(parameters[6]);
		
		soundId = parameters[8];
		attackSoundUsed = Integer.parseInt(parameters[9]) == 1;
		
		frameCount = Integer.parseInt(parameters[10]);
		angleCount = Integer.parseInt(parameters[11]);
		
		frameRate = Double.parseDouble(parameters[12]);
		replayDelay = Double.parseDouble(parameters[13]);
		
		sequenceType = Integer.parseInt(parameters[14]);
		id = Integer.parseInt(parameters[15]);
		
		mirroringMode = Integer.parseInt(parameters[16]);
		
		String deltaFilename = parameters[17].substring(parameters[17].indexOf('/') + 1);
		File deltaFile = new File(graphicsDeltaRoot, deltaFilename);
		try(BufferedReader reader = new BufferedReader(new FileReader(deltaFile))) {
			deltas = reader.lines().filter(CsvFilter::filter)
						.map(CsvFilter::map)
						.map(FunctionWrapper.highOrder(Integer::parseInt, Integer[]::new))
						.map(deltaParams -> new GraphicsDelta(deltaParams[0], deltaParams[1], deltaParams[2]))
						.toArray(GraphicsDelta[]::new);
		}
		assert Integer.parseInt(parameters[7]) == deltas.length;
	}
}
