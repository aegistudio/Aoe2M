#include "priority.hdr.glsl"

uniform sampler2D texNormal;
uniform sampler2D texPlayer;
varying vec2 texCoordVarying;

bool or(bool left, bool right) {
	if(left) return true;
	return right;
}

void main() {
	bool fill = false;
	fill = or(fill, texture2D(texNormal, texCoordVarying).a >= 0.5);
	fill = or(fill, texture2D(texPlayer, texCoordVarying).a >= 0.5);
	
	if(fill) gl_FragColor = vec4(priority());
	else discard;
}