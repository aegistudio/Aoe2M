package net.aegistudio.aoe2m.wyvern.unit;

import static org.lwjgl.opengl.ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
import static org.lwjgl.opengl.ARBVertexShader.GL_VERTEX_SHADER_ARB;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.asset.ShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class ProfileVisqProgram extends ShaderProgram {
	public ProfileVisqProgram() throws IOException {
		super.loadSource(GL_VERTEX_SHADER_ARB, "profileVisq.vsh.glsl",
				getClass().getResourceAsStream("/profileVisq.vsh.glsl"));
		super.loadSource(GL_FRAGMENT_SHADER_ARB, "profileVisq.fsh.glsl",
				getClass().getResourceAsStream("/profileVisq.fsh.glsl"));
	}

	public SamplerBinding profileMap;
	public void create() throws LWJGLException {
		super.create();
		
		profileMap = new SamplerBinding(0, 
				super.uniform("profileMap"), 
				super.vertexAttribute("texCoordInput"));
	}
}
