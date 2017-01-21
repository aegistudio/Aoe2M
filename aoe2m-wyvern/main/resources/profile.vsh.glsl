#include "priority.hdr.glsl"

attribute vec2 texCoordInput;
varying vec2 texCoordVarying;

void main() {
	gl_Position = ftransform();
	float depth = priority();
	texCoordVarying = texCoordInput;
	gl_Position.z = 2.0 * depth - 1.0;
}