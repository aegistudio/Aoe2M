package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBMultitexture.*;
import static org.lwjgl.opengl.ARBVertexShader.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class ObstructShaderProgram extends ShaderProgram {
	public final PriorityShaderObjects priority;
	public final PriorityMapShaderObjects priorityMap;
	public final PlayerPaletteObjects playerPalette;
	
	public ObstructShaderProgram(PlayerPaletteBuffer paletteBuffer) throws IOException {	
		super.loadSource("obstruct.vsh.glsl", getClass(), GL_VERTEX_SHADER_ARB);
		super.loadSource("obstruct.fsh.glsl", getClass(), GL_FRAGMENT_SHADER_ARB);
		
		super.loadSource("quantization.hdr.glsl", getClass());
		super.loadSource("quantization.glsl", getClass(), GL_FRAGMENT_SHADER_ARB);
		
		priorityMap = new PriorityMapShaderObjects(1);
		priorityMap.loadObjects(this);
		
		priority = new PriorityShaderObjects(GL_FRAGMENT_SHADER_ARB);
		priority.loadObjects(this);
		
		playerPalette = new PlayerPaletteObjects(paletteBuffer, GL_FRAGMENT_SHADER_ARB);
		playerPalette.loadObjects(this);
	}
	
	public SamplerBinding obstruct;
	public int outline;
	public void create() throws LWJGLException {
		super.create();
		
		obstruct = new SamplerBinding(0, uniform("obstruct"), 
				vertexAttribute("texCoordInput"));
		outline = uniform("outline");
		
		playerPalette.create(this);
		priority.create(this);
		priorityMap.create(this);
	}
	
	public void outline(int index) {
		glUniform1iARB(outline, index);
	}
	
	public void use() throws LWJGLException {
		super.use();
		
		obstruct.enable();
		priorityMap.map.enable();
		
		playerPalette.palette();
	}
	
	public void unuse() throws LWJGLException {
		glActiveTextureARB(GL_TEXTURE0_ARB);
		super.unuse();
	}
}