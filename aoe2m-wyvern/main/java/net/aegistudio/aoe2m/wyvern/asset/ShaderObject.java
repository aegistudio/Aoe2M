package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBShaderObjects.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.lwjgl.LWJGLException;

public class ShaderObject {
	public static final int MAX_INFO_LENGTH = 128;
	
	public final String source;
	public String preprocess;
	public final int[] linkage;
	public final int[] object;
	public final String title;
	
	public ShaderObject(String title, InputStream inputStream, int... linkage) throws IOException{
		this.title = title;
		StringBuilder sourceBuilder = new StringBuilder();
		new BufferedReader(new InputStreamReader(inputStream))
			.lines().forEachOrdered(line -> sourceBuilder.append(line).append("\n"));
		
		this.source = new String(sourceBuilder);
		this.linkage = linkage;
		this.object = new int[linkage.length];
	}
	
	public void preprocess(List<ShaderObject> objects) {
		this.preprocess = source;
		for(ShaderObject object : objects) {
			CharSequence objectSource = object.source;
			CharSequence includeQuote = "#include \"" + object.title + "\"";
			CharSequence includeBracket = "#include <" + object.title + ">";
			
			preprocess = preprocess.replace(includeQuote, objectSource);
			preprocess = preprocess.replace(includeBracket, objectSource);
		}
	}
	
	public void compile() throws LWJGLException {
		for(int i = 0; i < linkage.length; i ++) {
			object[i] = glCreateShaderObjectARB(linkage[i]);
			glShaderSourceARB(object[i], preprocess);
			glCompileShaderARB(object[i]);
			
			String log = glGetInfoLogARB(object[i], 
					MAX_INFO_LENGTH);
			if(log != null && log.length() > 0)
				throw new LWJGLException(log);
		}
	}
	
	public void attach(int programObject) throws LWJGLException {
		for(int target : object)
			glAttachObjectARB(programObject, target);
	}
	
	public void detach(int programObject) throws LWJGLException {
		for(int target : object)
			glDetachObjectARB(programObject, target);
	}
	
	public void delete() throws LWJGLException {
		for(int i = 0; i < object.length; i ++) {
			glDeleteObjectARB(object[i]);
			object[i] = 0;
		}
	}
}
