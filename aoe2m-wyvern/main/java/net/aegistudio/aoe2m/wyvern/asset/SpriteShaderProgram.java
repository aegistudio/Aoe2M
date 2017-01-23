package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBMultitexture.*;
import static org.lwjgl.opengl.ARBVertexShader.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class SpriteShaderProgram extends ShaderProgram {
	public final PriorityShaderObjects priority;
	public final PriorityMapShaderObjects priorityMap;
	
	public SpriteShaderProgram() throws IOException {	
		super.loadSource("sprite.vsh.glsl", getClass(), GL_VERTEX_SHADER_ARB);
		super.loadSource("sprite.fsh.glsl",	getClass(), GL_FRAGMENT_SHADER_ARB);
		
		super.loadSource("quantization.hdr.glsl", getClass());
		super.loadSource("quantization.glsl", getClass(), GL_FRAGMENT_SHADER_ARB);
		
		priorityMap = new PriorityMapShaderObjects(1);
		priorityMap.loadObjects(this);
		
		priority = new PriorityShaderObjects(GL_FRAGMENT_SHADER_ARB);
		priority.loadObjects(this);
	}
	
	public SamplerBinding normal;
	public void create() throws LWJGLException {
		super.create();
		
		normal = new SamplerBinding(0, uniform("sprite"), 
				vertexAttribute("texCoordInput"));
		
		priority.create(this);
		priorityMap.create(this);
	}
	
	public void use() throws LWJGLException {
		super.use();
		
		normal.enable();
		priorityMap.map.enable();
	}
	
	public void unuse() throws LWJGLException {
		glActiveTextureARB(GL_TEXTURE0_ARB);
		super.unuse();
	}
}