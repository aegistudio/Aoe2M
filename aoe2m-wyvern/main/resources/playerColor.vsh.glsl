#extension GL_ARB_uniform_buffer_object : require

#include "priorityMap.hdr.glsl"
#include "quantization.hdr.glsl"

attribute vec2 texCoordInput;
varying vec2 texCoordVarying;

void main() {
	gl_Position = ftransform();
	texCoordVarying = texCoordInput;
	priomapVertex();
}