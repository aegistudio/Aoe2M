attribute float coordX, coordY;
varying float locationX, locationY;

void main() {
	gl_Position = ftransform();
	locationX = coordX;
	locationY = coordY;
}