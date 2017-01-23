package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBVertexShader.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class PriorityMapShaderObjects {
	public final int priorityMapIndex;
	public final ShaderObject header, vertex, fragment;
	public PriorityMapShaderObjects(int mapIndex) throws IOException {
		priorityMapIndex = mapIndex;
		header = new ShaderObject("priorityMap.hdr.glsl", getClass());
		vertex = new ShaderObject("priorityMap.vsh.glsl", getClass(), GL_VERTEX_SHADER_ARB);
		fragment = new ShaderObject("priorityMap.fsh.glsl", getClass(), GL_FRAGMENT_SHADER_ARB);
	}
	
	public void loadObjects(ShaderProgram program) {
		program.loadObject(header);
		program.loadObject(vertex);
		program.loadObject(fragment);
	}
	
	public SamplerBinding map;
	public void create(ShaderProgram program) throws LWJGLException {
		map = new SamplerBinding(priorityMapIndex, 
				program.uniform("priorityMap"), 0);
	}
}
