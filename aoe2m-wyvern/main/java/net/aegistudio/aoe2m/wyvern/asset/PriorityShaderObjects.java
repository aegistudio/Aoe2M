package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBVertexShader.*;
import java.io.IOException;
import org.lwjgl.LWJGLException;

public class PriorityShaderObjects {
	public final ShaderObject header, source;
	public PriorityShaderObjects(int... linkage) throws IOException {
		header = new ShaderObject("priority.hdr.glsl", PriorityShaderObjects.class
				.getResourceAsStream("/priority.hdr.glsl"), linkage);
		source = new ShaderObject("priority.glsl", PriorityShaderObjects.class
				.getResourceAsStream("/priority.glsl"), linkage);
	}
	
	public void loadObjects(ShaderProgram program) {
		program.loadObject(header);
		program.loadObject(source);
	}
	
	protected int priority;
	protected int priorityBottom, priorityTop;
	public void create(ShaderProgram program) throws LWJGLException {
		priority = program.uniform("priorityValue");
		priorityBottom = program.uniform("priorityBottom");
		priorityTop = program.uniform("priorityTop");
	}
	
	public void boundary(float bottom, float top) {
		glVertexAttrib1fARB(priorityBottom, bottom);
		glVertexAttrib1fARB(priorityTop, top);
	}
	
	public void priority(float priority) {
		glVertexAttrib1fARB(this.priority, priority);
	}
}