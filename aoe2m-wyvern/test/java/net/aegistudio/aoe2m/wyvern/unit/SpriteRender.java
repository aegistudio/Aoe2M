package net.aegistudio.aoe2m.wyvern.unit;

import java.io.IOException;
import java.util.Arrays;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.GraphicsDelta;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.wyvern.terrain.BlendingRender;

public class SpriteRender extends BlendingRender {
	protected final GraphicsManager graphicsManager;
	protected final SpriteRenderer spriteRenderer;
	
	public SpriteRender() throws IOException, LWJGLException {
		super();
		graphicsManager = new GraphicsManager(connection);
		spriteRenderer = new SpriteRenderer(outline, graphicsManager, textureManager);
	}
	
	public void render() throws LWJGLException {
		super.render();
		
		GraphicsGamedata unit = connection.graphics().query(6747);
		GraphicsDelta[] populate = new GraphicsDelta[unit.deltas.length + 1];
		populate[0] = new GraphicsDelta(unit.id, 0, 0);
		System.arraycopy(unit.deltas, 0, populate, 1, unit.deltas.length);
		populate = Arrays.stream(populate)
			.filter(item -> connection.graphics().query(item.deltaGraphic) != null)
			.sorted((item1, item2) -> {
				int ly1 = connection.graphics().query(item1.deltaGraphic).layer.ordinal();
				int ly2 = connection.graphics().query(item2.deltaGraphic).layer.ordinal();
				return ly1 - ly2;
			}).toArray(GraphicsDelta[]::new);
		
		spriteRenderer.prepare();
		for(GraphicsDelta item : populate) 
			spriteRenderer.draw(terrain, 7 + item.directionX, 
					7 + item.directionY, 0, item.deltaGraphic, 0, 0);
		spriteRenderer.cleanup();
	}

}
