package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBMultitexture.*;
import static org.lwjgl.opengl.ARBVertexShader.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class ProfileShaderProgram extends ShaderProgram {
	public final PriorityShaderObjects priority;
	public ProfileShaderProgram() throws IOException {	
		super.loadSource("profile.vsh.glsl", getClass(), GL_VERTEX_SHADER_ARB);
		super.loadSource("profile.fsh.glsl", getClass(), GL_FRAGMENT_SHADER_ARB);
		super.loadSource("quantization.glsl", getClass(), GL_FRAGMENT_SHADER_ARB);
		
		super.loadSource("quantization.hdr.glsl", getClass());
		
		priority = new PriorityShaderObjects(
				GL_VERTEX_SHADER_ARB, GL_FRAGMENT_SHADER_ARB);
		priority.loadObjects(this);
	}
	
	public SamplerBinding normal, player, obstruct;
	public int texCoord;
	public void create() throws LWJGLException {
		super.create();
			
		priority.create(this);
		
		normal = new SamplerBinding(0, 
				uniform("texNormal"), 
				vertexAttribute("texCoordInput"));
		
		player = new SamplerBinding(1, 
				uniform("texPlayer"), 
				vertexAttribute("texCoordInput"));
		
		obstruct = new SamplerBinding(2, 
				uniform("texObstruct"), 
				vertexAttribute("texCoordInput"));
		
		texCoord = vertexAttribute("texCoordInput");
	}
	
	public void texCoord(double u, double v) {
		glVertexAttrib2fARB(texCoord, (float)u, (float)v);
	}
	
	public void use() throws LWJGLException {
		super.use();
		
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		normal.enable();
		player.enable();
	}
	
	public void unuse() throws LWJGLException {
		GL11.glPopAttrib();
		
		glActiveTextureARB(GL_TEXTURE0_ARB);
		super.unuse();
	}
}