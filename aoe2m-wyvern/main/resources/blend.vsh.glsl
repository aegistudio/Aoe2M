attribute vec2 maskCoord;
attribute vec2 neighbourCoord;

varying vec2 maskMapping;
varying vec2 neighbourMapping;

void main() {
	gl_Position = ftransform();
	maskMapping = maskCoord;
	neighbourMapping = neighbourCoord;
}
