#include "priority.hdr.glsl"

attribute vec2 texCoordInput;
varying vec2 texCoordVarying;

void main() {
	gl_Position = ftransform();
	texCoordVarying = texCoordInput;
	gl_Position.z = 1.0 - priority();
}