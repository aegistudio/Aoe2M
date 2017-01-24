package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.*;
import static org.lwjgl.opengl.ARBUniformBufferObject.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

public class ShaderProgram {
	protected boolean valid = false;
	protected boolean verify = true;
	protected int programObject = 0;
	protected final List<ShaderObject> objects = new ArrayList<>();
	
	protected void verifyFlag(boolean flag) {
		verify = flag;
	}
	
	protected void loadSource(int type, String title, InputStream file) throws IOException {
		loadSource(file, title, type);
	}
	
	protected void loadSource(InputStream file, String title, int... type) throws IOException {
		loadObject(new ShaderObject(title, file, type));
	}
	
	protected void loadSource(String title, Class<?> clazz, int... type) throws IOException {
		loadObject(new ShaderObject(title, clazz, type));
	}
	
	protected void loadObject(ShaderObject custom) {
		objects.add(custom);
	}
	
	public void create() throws LWJGLException {
		if(valid) return;
		for(ShaderObject object : objects) {
			object.preprocess(objects);
			object.compile();
		}
		
		programObject = glCreateProgramObjectARB();
		for(ShaderObject object : objects) 
			object.attach(programObject);
		
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
		for(ShaderObject object : objects) {
			object.detach(programObject);
			object.delete();
		}
		glDeleteObjectARB(programObject);
	}
	
	protected int verifyAddress(int address, String name) throws LWJGLException {
		if(verify) if(address == GL_INVALID_INDEX) 
			throw new LWJGLException(name);
		return address;
	}
	
	public int uniform(String name) throws LWJGLException {
		return verifyAddress(glGetUniformLocationARB(programObject, name), 
				"shader.missingUniform." + name);
	}
	
	public int vertexAttribute(String name) throws LWJGLException {
		return verifyAddress(glGetAttribLocationARB(programObject, name),
				"shader.missingAttribute." + name);
	}
	
	public int uniformBlock(String name) throws LWJGLException {
		return verifyAddress(glGetUniformBlockIndex(programObject, name),
				"shader.missingUniformBlock." + name);
	}
}
