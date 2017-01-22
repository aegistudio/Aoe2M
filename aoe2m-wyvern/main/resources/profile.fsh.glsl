#include "priority.hdr.glsl"
#include "quantization.hdr.glsl"

uniform sampler2D texNormal;
uniform sampler2D texPlayer;
varying vec2 texCoordVarying;
uniform bool enable;

bool or(bool left, bool right) {
	if(left) return true;
	return right;
}

const float multiplier = 16.0;

void main() {
	bool fill = false;
	fill = or(fill, texture2D(texNormal, texCoordVarying).a >= 0.5);
	fill = or(fill, texture2D(texPlayer, texCoordVarying).a >= 0.5);
	
	if(fill) 
		gl_FragColor = quantize(priority(), multiplier);
	else discard;
}