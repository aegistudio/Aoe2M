#include "priorityMap.hdr.glsl"

void priomapVertex() {
	vec2 screenCoord = gl_Position.xy;
	priorityCoord = 0.5 * (screenCoord + 1.0);
}