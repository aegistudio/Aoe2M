varying float coordX, coordY, coordZ;
attribute float x, y, elevation;

void main() {
	coordX = x;
	coordY = y;
	coordZ = elevation;
	gl_Position = ftransform();
}