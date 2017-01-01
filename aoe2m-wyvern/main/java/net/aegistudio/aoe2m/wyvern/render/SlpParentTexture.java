package net.aegistudio.aoe2m.wyvern.render;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Supplier;

import org.lwjgl.LWJGLException;

public class SlpParentTexture implements ParentTexture {
	protected final GlTexture texture;
	protected final SlpTexture[] subTextures;
	public SlpParentTexture(Supplier<BufferedImage> imageSupplier, InputStream descriptor) throws IOException {
		this.texture = new GlTexture(imageSupplier);
		BufferedImage image = imageSupplier.get();	// We won't recalculate on reload.
		
		this.subTextures = new BufferedReader(new InputStreamReader(descriptor)).lines().map(line -> {
			if(line.length() == 0) return null;
			if(line.startsWith("#")) return null;
			Double[] numbers = Arrays.stream(line.split(","))
					.map(Double::parseDouble).toArray(Double[]::new);
			
			double x = numbers[0];		double left = 		(x + 0) / image.getWidth();
			double y = numbers[1];		double bottom = 	(y + 0) / image.getHeight();
			double w = numbers[2];		double right =	 	(x + w) / image.getWidth();
			double h = numbers[3];		double top = 		(y + h) / image.getHeight();
			double cx = numbers[4];		double centerx =	(x + cx) / image.getWidth();
			double cy = numbers[5];		double centery = 	(y + cy) / image.getHeight();
			
			return new SlpTexture(left, right, bottom, top, centerx, centery);
		}).filter(slp -> slp != null).toArray(SlpTexture[]::new);
	}
	
	public void make(int id) throws LWJGLException {	texture.make(id);	}
	
	private void cannotBind() {	throw new AssertionError("bind.parentTexture"); }
	public void bottomLeft(Coordinator bind) throws LWJGLException {	cannotBind();	}
	public void bottomRight(Coordinator bind) throws LWJGLException {	cannotBind();	}
	public void topRight(Coordinator bind) throws LWJGLException {		cannotBind();	}
	public void topLeft(Coordinator bind) throws LWJGLException {		cannotBind();	}
	public void center(Coordinator bind) throws LWJGLException {		cannotBind();	}
	
	public SlpTexture get(int index) {	return subTextures[index];	}
	public int count() { return subTextures.length;	}
}
