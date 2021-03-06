#include "priority.hdr.glsl"
#include "quantization.hdr.glsl"

uniform sampler2D texNormal;
uniform sampler2D texPlayer;
uniform sampler2D texObstruct;

varying vec2 texCoordVarying;
uniform bool enable;

bool or(bool left, bool right) {
	if(left) return true;
	return right;
}

void main() {
	bool fill = false;
	fill = or(fill, texture2D(texNormal, texCoordVarying).a >= 0.5);
	fill = or(fill, texture2D(texPlayer, texCoordVarying).a >= 0.5);
	fill = or(fill, texture2D(texObstruct, texCoordVarying).a >= 0.5);
	
	if(fill) 
		gl_FragColor = quantize(priority(), defaultFactor);
	else discard;
}