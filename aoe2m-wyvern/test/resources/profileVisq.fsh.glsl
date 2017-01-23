uniform sampler2D profileMap;
varying vec2 texCoordVarying;
uniform vec4 mask;

void main() {
	vec4 tile = texture2D(profileMap, texCoordVarying);
	if(tile.a < 0.5) discard;
	else gl_FragColor = vec4(
		tile.r * mask.r, tile.g * mask.g, tile.b * mask.b, 1.0);
}