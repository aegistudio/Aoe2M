package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBMultitexture.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.*;

import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class StickyShaderProgram extends ShaderProgram {
	public StickyShaderProgram() throws IOException {	
		super.loadSource(GL_VERTEX_SHADER_ARB, 
				getClass().getResourceAsStream("/sticky.vsh.glsl"));
		super.loadSource(GL_FRAGMENT_SHADER_ARB, 
				getClass().getResourceAsStream("/sticky.fsh.glsl"));
	}
	

	protected int stickyLbX, stickyRtX, coordX;
	protected int stickyLbY, stickyRtY, coordY;
	
	public SamplerBinding stickyTexture;
	public void create() throws LWJGLException {
		super.create();
		stickyLbX = uniform("stickyLbX");
		stickyLbY = uniform("stickyLbY");
		
		stickyRtX = uniform("stickyRtX");
		stickyRtY = uniform("stickyRtY");
		
		coordX = vertexAttribute("coordX");
		coordY = vertexAttribute("coordY");
		
		stickyTexture = new SamplerBinding(0, 
				uniform("stickyTexture"), 0);
	}
	
	public void size(float x, float y, float w, float h) {
		glUniform1fARB(stickyLbX, x + 0);
		glUniform1fARB(stickyLbY, y + 0);
		glUniform1fARB(stickyRtX, x + w);
		glUniform1fARB(stickyRtY, y + h);
	}
	
	public void coord(float x, float y) {
		glVertexAttrib1fARB(coordX, x);
		glVertexAttrib1fARB(coordY, y);
	}
	
	public void use() throws LWJGLException {
		super.use();
		
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		stickyTexture.enable();
	}
	
	public void unuse() throws LWJGLException {	
		glActiveTextureARB(GL_TEXTURE0_ARB);
		GL11.glPopAttrib();
		super.unuse();
	}
}
