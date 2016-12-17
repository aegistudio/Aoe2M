package net.aegistudio.aoe2m.scx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.Assert.*;
import javax.imageio.ImageIO;

public class CinematicImageWrite extends ScenarioWriteBase {

	public CinematicImageWrite() {	super("cinematicImageWrite.scx");	}

	@Override
	public void todo(Scenario scenario) {
		try {
			BufferedImage image = ImageIO.read(
				getClass().getResourceAsStream("/cinematicImage.bmp"));
			assertNotNull(image);
			scenario.cinematic.bitmap.setValue(image);
		}
		catch(IOException e) {
			assertTrue(false);
		}
	}

}
