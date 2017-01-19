package net.aegistudio.aoe2m.wyvern.asset;

import static org.lwjgl.opengl.ARBVertexShader.*;
import java.io.IOException;
import org.lwjgl.LWJGLException;

public class PriorityShaderObject {
	public void source(ShaderProgram program, int type) throws IOException {
		program.loadSource(type, getClass()
				.getResourceAsStream("/priority.glsl"));
	}
	
	protected int priority;
	protected int priorityBottom, priorityTop;
	public void create(ShaderProgram program) throws LWJGLException {
		priority = program.uniform("priority");
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