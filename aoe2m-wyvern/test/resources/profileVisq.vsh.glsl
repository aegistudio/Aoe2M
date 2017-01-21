attribute vec2 texCoordInput;
varying vec2 texCoordVarying;

void main() {
	gl_Position = ftransform();
	texCoordVarying = texCoordInput;
}