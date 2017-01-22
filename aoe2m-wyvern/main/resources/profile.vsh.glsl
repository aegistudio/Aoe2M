#include "priority.hdr.glsl"

attribute vec2 texCoordInput;
varying vec2 texCoordVarying;

void main() {
	gl_Position = ftransform();
	texCoordVarying = texCoordInput;
	gl_Position.z = 2.0 * priority() - 1.0;
}