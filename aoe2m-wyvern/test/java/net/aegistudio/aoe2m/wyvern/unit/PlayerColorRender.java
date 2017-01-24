package net.aegistudio.aoe2m.wyvern.unit;

import java.io.IOException;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.asset.PlayerColorShaderProgram;
import net.aegistudio.aoe2m.wyvern.asset.PlayerPaletteBuffer;

public class PlayerColorRender extends SpriteRender {
	public final PlayerPaletteBuffer paletteBuffer;
	public final PlayerColorShaderProgram paletteProgram;
	public final PlayerColorRenderer paletteRenderer;
	
	public PlayerColorRender() throws IOException, LWJGLException {
		super();
		super.placement = new PlacementConsole(
				connection.graphics(), graphicsManager) {
			
			public void consoleWelcome() {
				System.out.println("Please entry the graphic id to render.");
				System.out.println("Format: <id> <x> <y> <z> <frame> <angle> [<player>]");
			}
			
			public GraphicsInstruction[] parse(String[] p) {
				GraphicsInstruction[] previous = super.parse(p);
				if(p.length >= 7) 
					for(GraphicsInstruction item : previous) 
						item.hint.put("playerColor.index", Integer.parseInt(p[6]));
				return previous;
			}
		};
		paletteBuffer = new PlayerPaletteBuffer(connection::playerPalette);
		paletteProgram = new PlayerColorShaderProgram(paletteBuffer);
		paletteRenderer = new PlayerColorRenderer(paletteProgram, profileMap, 
				arbitrator, graphicsManager, biasOutline, textureManager);
	}
	
	public void prepare() throws LWJGLException {
		super.prepare();
		paletteBuffer.create();
		paletteProgram.create();
	}
	
	public void render() throws LWJGLException {
		super.render();
		placement.render(paletteRenderer, terrain);
	}
	
	public void dispose() throws LWJGLException {
		super.dispose();
		paletteBuffer.destroy();
		paletteProgram.destroy();
	}
}
