uniform sampler2D profileMap;
varying vec2 texCoordVarying;

void main() {
	vec4 tile = texture2D(profileMap, texCoordVarying);
	if(tile.a < 0.5) discard;
	else gl_FragColor = tile;
}