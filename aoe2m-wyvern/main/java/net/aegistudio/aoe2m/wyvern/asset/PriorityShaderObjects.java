package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBShaderObjects.*;
import java.io.IOException;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.render.SamplerBinding;

public class PriorityShaderObjects {
	public final ShaderObject header, source;
	public PriorityShaderObjects(int... linkage) throws IOException {
		header = new ShaderObject("priority.hdr.glsl", getClass());
		source = new ShaderObject("priority.glsl", getClass(), linkage);
	}
	
	public void loadObjects(ShaderProgram program) {
		program.loadObject(header);
		program.loadObject(source);
	}
	
	protected int priorityMapIndex = -1;
	public void usePriorityMap(int binding) {
		priorityMapIndex = binding;
	}
	
	protected int priority;
	protected int bottom, top;
	public SamplerBinding priorityMap;
	public void create(ShaderProgram program) throws LWJGLException {
		priority = program.uniform("priorityValue");
		bottom = program.uniform("priorityBottom");
		top = program.uniform("priorityTop");
		
		if(priorityMapIndex >= 0)
			priorityMap = new SamplerBinding(priorityMapIndex, 
					program.uniform("priorityMap"), 0);
	}
	
	public void boundary(float bottom, float top) {
		glUniform1fARB(this.bottom, bottom);
		glUniform1fARB(this.top, top);
	}
	
	public void priority(float priority) {
		glUniform1fARB(this.priority, priority);
	}
}