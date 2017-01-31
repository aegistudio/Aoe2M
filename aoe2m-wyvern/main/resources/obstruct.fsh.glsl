#include "priority.hdr.glsl"
#include "priorityMap.hdr.glsl"
#include "quantization.hdr.glsl"
#include "playerPalette.hdr.glsl"

varying vec2 texCoordVarying;
uniform int outline;
uniform sampler2D obstruct;

void main() {
	priomapFragment();
	vec4 color = texture2D(obstruct, texCoordVarying);
	if(color.a < 0.5) { discard; return; }
	
	if(priomapCompare(priority(), defaultFactor) < 0)
		gl_FragColor = paletteQuery(outline) + vec4(0.5);
	else { discard; return; }
}