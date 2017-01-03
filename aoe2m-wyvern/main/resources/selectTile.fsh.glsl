uniform float sizeX, sizeY, sizeZ;
varying float coordX, coordY, coordZ;

void main() {
	gl_FragColor = vec4(coordX / sizeX,
		coordY / sizeY, 
		coordZ / sizeZ, 1.0);
}
