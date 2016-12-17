package net.aegistudio.aoe2m.scx;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestCinematicImage extends ScenarioReadBase {

	public TestCinematicImage() {	super("/cinematicImage.scx", "gbk");	}

	@Override
	protected void todo(Scenario scenario) {
		assertNotNull(scenario.cinematic.bitmap.getValue());
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ImageIO.write(scenario.cinematic.bitmap.getValue(), "bmp", buffer);
			byte[] readImage = buffer.toByteArray();
			
			BufferedImage compareImageObj = ImageIO
					.read(getClass().getResourceAsStream("/cinematicImage.bmp"));
			ByteArrayOutputStream compareBuffer = new ByteArrayOutputStream();
			ImageIO.write(compareImageObj, "bmp", compareBuffer);
			byte[] compareImage = compareBuffer.toByteArray();
			
			assertEquals(readImage.length, compareImage.length);
			for(int i = 0; i < readImage.length; i ++)
				assertEquals(readImage[i], compareImage[i]);
		}
		catch(IOException e) {
			assertTrue(false);
		}
	}
}
