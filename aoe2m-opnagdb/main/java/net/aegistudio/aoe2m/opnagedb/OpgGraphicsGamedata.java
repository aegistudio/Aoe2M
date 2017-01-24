package net.aegistudio.aoe2m.opnagedb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.EnumLayer;
import net.aegistudio.aoe2m.assetdba.GraphicsDelta;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.media.Storage;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;

public class OpgGraphicsGamedata extends GraphicsGamedata {
	public OpgGraphicsGamedata(AssetListener perfLog, OpgPlayerPalette playerPalette, Storage graphicsRoot, 
			Storage graphicsDeltaRoot, String[] parameters) throws IOException {
		
		id = Integer.parseInt(parameters[15]);
		perfLog.initAsset(GRAPHICS_NAME, GRAPHICS_CLASS, id);
		
		name0 = parameters[0];
		name1 = parameters[1];
		
		slp = OpgSlpPaletteImage.open(
				() -> perfLog.initArchive(GRAPHICS_NAME, GRAPHICS_CLASS, id),
				() -> perfLog.readyArchive(GRAPHICS_NAME, GRAPHICS_CLASS, id), 
				graphicsRoot, parameters[2] + ".slp", playerPalette);
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
		
		mirroringMode = Integer.parseInt(parameters[16]);
		
		String deltaFilename = parameters[17].substring(parameters[17].indexOf('/') + 1);
		Storage deltaFile = graphicsDeltaRoot.chdir(deltaFilename);
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(deltaFile.read()))) {
			deltas = reader.lines().filter(CsvFilter::filter)
						.map(CsvFilter::map)
						.map(FunctionWrapper.highOrder(Integer::parseInt, Integer[]::new))
						.map(deltaParams -> new GraphicsDelta(deltaParams[0], deltaParams[1], deltaParams[2]))
						.toArray(GraphicsDelta[]::new);
		}
		assert Integer.parseInt(parameters[7]) == deltas.length;
		
		perfLog.readyAsset(GRAPHICS_NAME, GRAPHICS_CLASS, id);
	}
}
