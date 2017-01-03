package net.aegistudio.aoe2m.wyvern.render;

import static org.lwjgl.opengl.ARBMultitexture.GL_TEXTURE0_ARB;
import static org.lwjgl.opengl.ARBMultitexture.glActiveTextureARB;
import static org.lwjgl.opengl.ARBShaderObjects.glUniform1iARB;
import static org.lwjgl.opengl.ARBVertexShader.glVertexAttrib2dARB;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

public class SamplerBinding implements TextureManager.TextureBind, Coordinator {
	protected final int multitexIndex;
	protected final int variableLocation, attributeLocation;
	
	public SamplerBinding(int multitexIndex, int variableLocation, int attributeLocation) {
		this.multitexIndex = multitexIndex;
		this.variableLocation = variableLocation;
		this.attributeLocation = attributeLocation;
	}
	
	public void enable() throws LWJGLException {
		glUniform1iARB(variableLocation, multitexIndex);
	}
	
	@Override
	public void bind(int index) throws LWJGLException {
		glActiveTextureARB(GL_TEXTURE0_ARB + multitexIndex);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, index);
	}

	@Override
	public void coord(double u, double v) throws LWJGLException {
		glVertexAttrib2dARB(attributeLocation, u, v);
	}
}
