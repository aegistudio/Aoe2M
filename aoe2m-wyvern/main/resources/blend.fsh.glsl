uniform sampler2D texture;
uniform sampler2D mask;

varying vec2 maskMapping;
varying vec2 neighbourMapping;

void main() {
	vec4 neighbour = texture2D(texture, neighbourMapping);
	float magnitude = texture2D(mask, maskMapping).s;
	gl_FragColor = vec4(neighbour.xyz, 1.0 - magnitude);
}