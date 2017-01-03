package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

public class ShaderProgram {
	protected boolean valid = false;
	protected int programObject = 0;
	protected final Map<Integer, String> sources = new TreeMap<>();
	protected final Map<Integer, Integer> objects = new TreeMap<>();
	
	protected void loadSource(int type, InputStream file) throws IOException {
		StringBuilder vertexSourceBuilder = new StringBuilder();
		new BufferedReader(new InputStreamReader(file))
			.lines().forEachOrdered(line -> vertexSourceBuilder.append(line).append("\n"));
		sources.put(type, new String(vertexSourceBuilder));
	}
	
	protected void compileSource(int type, String source) throws LWJGLException {
		int object = glCreateShaderObjectARB(type);
		glShaderSourceARB(object, sources.get(type));
		glCompileShaderARB(object);
		
		String log = glGetInfoLogARB(object, 128);
		if(log != null && log.length() > 0)
			throw new LWJGLException(log);
	
		objects.put(type, object);
	}
	
	public void create() throws LWJGLException {
		if(valid) return;
		for(Map.Entry<Integer, String> source : sources.entrySet())
			compileSource(source.getKey(), source.getValue());
		
		programObject = glCreateProgramObjectARB();
		for(Map.Entry<Integer, Integer> object : objects.entrySet())
			glAttachObjectARB(programObject, object.getValue());
		
		glLinkProgramARB(programObject);
		if(GL11.glGetError() != GL11.GL_NO_ERROR)
			throw new LWJGLException("shader.linkage");
		
		valid = true;
	}
	
	public void use() throws LWJGLException {
		glUseProgramObjectARB(programObject);
	}
	
	public void unuse() throws LWJGLException {
		glUseProgramObjectARB(0);
	}
	
	public void destroy() throws LWJGLException {
		valid = false;
		for(Map.Entry<Integer, Integer> object : objects.entrySet()) 
			glDetachObjectARB(programObject, object.getValue());	
		for(Map.Entry<Integer, Integer> object : objects.entrySet()) 
			glDeleteObjectARB(object.getValue());
		objects.clear();
		glDeleteObjectARB(programObject);
	}
	
	public int uniform(String name) throws LWJGLException {
		return glGetUniformLocationARB(programObject, name);
	}
	
	public int vertexAttribute(String name) throws LWJGLException {
		return glGetAttribLocationARB(programObject, name);
	}
}
