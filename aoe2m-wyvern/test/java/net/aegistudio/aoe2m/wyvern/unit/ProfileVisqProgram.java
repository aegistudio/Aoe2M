package net.aegistudio.aoe2m.wyvern.unit;

import static org.lwjgl.opengl.ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
import static org.lwjgl.opengl.ARBVertexShader.GL_VERTEX_SHADER_ARB;
import static org.lwjgl.opengl.ARBShaderObjects.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.asset.ShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class ProfileVisqProgram extends ShaderProgram {
	public ProfileVisqProgram() throws IOException {
		loadSource("profileVisq.vsh.glsl", getClass(), GL_VERTEX_SHADER_ARB);
		loadSource("profileVisq.fsh.glsl", getClass(), GL_FRAGMENT_SHADER_ARB);
		verifyFlag(false);
	}

	public int mask;
	public SamplerBinding profileMap;
	public void create() throws LWJGLException {
		super.create();
		
		mask = uniform("mask");
		profileMap = new SamplerBinding(0, 
				super.uniform("profileMap"), 
				super.vertexAttribute("texCoordInput"));
	}
	
	public void mask(float r, float g, float b) {
		glUniform4fARB(mask, r, g, b, 1.0f);
	}
}
