#include "priority.hdr.glsl"
#include "priorityMap.hdr.glsl"
#include "quantization.hdr.glsl"

varying vec2 texCoordVarying;
uniform sampler2D sprite;

void main() {
	vec4 color = texture2D(sprite, texCoordVarying);
	if(color.a < 0.2) { discard; return; }
	
	if(priomapLessThan(priority(), defaultFactor)) { discard; return; }
	else gl_FragColor = color;
}