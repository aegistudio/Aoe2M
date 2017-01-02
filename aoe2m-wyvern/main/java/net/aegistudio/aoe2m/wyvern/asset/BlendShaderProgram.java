package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.*;
import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBMultitexture.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

public class BlendShaderProgram {
	protected boolean valid = false;
	protected int programObject = 0;
	
	protected String vertexSource, fragmentSource;
	protected int vertexShader = 0, fragmentShader = 0;
	
	private String loadSource(String file) {
		StringBuilder vertexSourceBuilder = new StringBuilder();
		new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)))
			.lines().forEachOrdered(line -> vertexSourceBuilder.append(line).append("\n"));
		return new String(vertexSourceBuilder);
	}
	
	private int compileSource(String source, int type, String exception) throws LWJGLException {
		int target = glCreateShaderObjectARB(type);
		glShaderSourceARB(target, source);
		glCompileShaderARB(target);
		
		if(GL11.glGetError() != GL11.GL_NO_ERROR)
			throw new LWJGLException(exception);
		
		String log = glGetInfoLogARB(target, 128);
		if(log != null && log.length() > 0)
			throw new LWJGLException(log);
		
		return target;
	}
	
	public BlendShaderProgram() throws IOException {
		vertexSource = loadSource("/blend.vsh.glsl");
		fragmentSource = loadSource("/blend.fsh.glsl");
	}
	
	int locTexture, locMask, locMaskCoord, locNeighbourCoord;
	public void create() throws LWJGLException {
		if(valid) return;
		vertexShader = compileSource(vertexSource, 
				GL_VERTEX_SHADER_ARB, "blendShader.vertexCompile");
		
		fragmentShader = compileSource(fragmentSource, 
				GL_FRAGMENT_SHADER_ARB, "blendShader.fragmentCompile");
				
		programObject = glCreateProgramObjectARB();
		glAttachObjectARB(programObject, vertexShader);
		glAttachObjectARB(programObject, fragmentShader);
		glLinkProgramARB(programObject);
		if(GL11.glGetError() != GL11.GL_NO_ERROR)
			throw new LWJGLException("blendShader.linkage");
		
		locTexture = glGetUniformLocationARB(programObject, "texture");
		locMask = glGetUniformLocationARB(programObject, "mask");
		locMaskCoord = glGetAttribLocationARB(programObject, "maskCoord");
		locNeighbourCoord = glGetAttribLocationARB(programObject, "neighbourCoord");
		
		valid = true;
	}
	
	public void use() {
		glUseProgramObjectARB(programObject);
		
		glUniform1iARB(locTexture, 0);	// texture
		glUniform1iARB(locMask, 1);		// mask
		
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_SRC_ALPHA);
	}
	
	public void unuse() {
		GL11.glPopAttrib();
		
		glActiveTextureARB(GL_TEXTURE0_ARB);
		glUseProgramObjectARB(0);
	}
	
	public void mask(int id) {
		glActiveTextureARB(GL_TEXTURE1_ARB);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
	public void neighbour(int id) {
		glActiveTextureARB(GL_TEXTURE0_ARB);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
	public void maskCoord(double x, double y) {
		glVertexAttrib2dARB(locMaskCoord, x, y);
	}
	
	public void neighbourCoord(double x, double y) {
		glVertexAttrib2dARB(locNeighbourCoord, x, y);
	}
	
	public void destroy() {
		valid = false;
		glDetachObjectARB(programObject, fragmentShader);
		glDetachObjectARB(programObject, vertexShader);
		glDeleteObjectARB(vertexShader);
		glDeleteObjectARB(programObject);
	}
}
