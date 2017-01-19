float priority();

attribute vec2 texCoordInput;
varying vec2 texCoordVarying;

void main() {
	gl_Position = ftransform();
	depth = priority();
	texCoordVarying = texCoordInput;
	gl_Position.z = 2 * depth - 1;
}