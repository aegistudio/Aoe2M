package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBShaderObjects.*;
import java.io.IOException;
import org.lwjgl.LWJGLException;

public class PlayerPaletteObjects {
	public final PlayerPaletteBuffer paletteBuffer;
	public final ShaderObject header, source;
	public PlayerPaletteObjects(PlayerPaletteBuffer paletteBuffer, int... linkage) throws IOException {
		this.paletteBuffer = paletteBuffer;
		header = new ShaderObject("playerPalette.hdr.glsl", getClass());
		source = new ShaderObject("playerPalette.glsl", getClass(), linkage);
	}
	
	public void loadObjects(ShaderProgram program) {
		program.loadObject(header);
		program.loadObject(source);
	}
	
	protected int playerPalette;
	protected int playerIndex;
	public void create(ShaderProgram program) throws LWJGLException {
		playerPalette = program.uniformBlock("playerPalette");
		playerIndex = program.uniform("playerIndex");
	}
	
	public void palette() {
		paletteBuffer.set(playerPalette);
	}
	
	public void player(int index) {
		glUniform1iARB(playerIndex, index);
	}
}
