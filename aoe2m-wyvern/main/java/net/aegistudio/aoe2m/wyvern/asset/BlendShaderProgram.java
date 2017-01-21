package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBVertexShader.*;
import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBMultitexture.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class BlendShaderProgram extends ShaderProgram {
	public BlendShaderProgram() throws IOException {	
		super.loadSource(GL_VERTEX_SHADER_ARB, "blend.vsh.glsl",
				getClass().getResourceAsStream("/blend.vsh.glsl"));
		super.loadSource(GL_FRAGMENT_SHADER_ARB, "blend.vsh.glsl",
				getClass().getResourceAsStream("/blend.fsh.glsl"));
	}
	
	public SamplerBinding neighbour, mask;
	public void create() throws LWJGLException {
		super.create();
		
		neighbour = new SamplerBinding(0, 
				uniform("texture"), 
				vertexAttribute("neighbourCoord"));
		
		mask = new SamplerBinding(1, 
				uniform("mask"), 
				vertexAttribute("maskCoord"));
	}
	
	public void use() throws LWJGLException {
		super.use();
		
		neighbour.enable();
		mask.enable();
		
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_SRC_ALPHA);
	}
	
	public void unuse() throws LWJGLException {
		GL11.glPopAttrib();
		
		glActiveTextureARB(GL_TEXTURE0_ARB);
		super.unuse();
	}
}
