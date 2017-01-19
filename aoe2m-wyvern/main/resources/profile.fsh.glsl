float priority();

uniform sampler2D normal;
uniform sampler2D player;
varying vec2 texCoordVarying;

void main() {
	bool fill = false;
	if(texture2D(normal, texCoordVarying).a >= 0.5) fill = true;
	if(texture2D(player, texCoordVarying).a >= 0.5) fill = true;
	
	if(fill) gl_FragDepth = true;
	else discard;
}