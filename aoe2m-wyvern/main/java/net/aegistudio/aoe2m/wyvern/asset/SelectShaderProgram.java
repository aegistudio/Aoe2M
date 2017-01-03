package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
import static org.lwjgl.opengl.ARBVertexShader.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;

public class SelectShaderProgram extends ShaderProgram {
	public SelectShaderProgram() throws IOException {	
		super.loadSource(GL_VERTEX_SHADER_ARB, 
				getClass().getResourceAsStream("/selectTile.vsh.glsl"));
		super.loadSource(GL_FRAGMENT_SHADER_ARB, 
				getClass().getResourceAsStream("/selectTile.fsh.glsl"));
	}

	protected int sizeX, sizeY, sizeZ;
	protected int x, y, elevation;
	public void create() throws LWJGLException {
		super.create();
		sizeX = uniform("sizeX");
		sizeY = uniform("sizeY");
		sizeZ = uniform("sizeZ");
		
		x = vertexAttribute("x");
		y = vertexAttribute("y");
		elevation = vertexAttribute("elevation");
	}
	
	public void coord(int x, int y, int z) {
		glVertexAttrib1fARB(this.x, x);
		glVertexAttrib1fARB(this.y, y);
		glVertexAttrib1fARB(this.elevation, z);
	}
	
	public void size(int x, int y, int z) {
		glUniform1fARB(sizeX, x);
		glUniform1fARB(sizeY, y);
		glUniform1fARB(sizeZ, z);
	}
}
