#include "priority.hdr.glsl"
#include "priorityMap.hdr.glsl"
#include "quantization.hdr.glsl"
#include "playerPalette.hdr.glsl"

varying vec2 texCoordVarying;
uniform sampler2D player;

void main() {
	priomapFragment();
	vec4 color = texture2D(player, texCoordVarying);
	if(color.a < 0.5) { discard; return; }
	
	if(priomapLessThan(priority(), defaultFactor)) { discard; return; }
	
	int index = 0;
	index += (color.r >= 0.5)? 1 : 0;
	index += (color.g >= 0.5)? 2 : 0;
	index += (color.b >= 0.5)? 4 : 0;
	gl_FragColor = paletteQuery(index);
}