#include "priority.hdr.glsl"
#include "priorityMap.hdr.glsl"

bool obstructed() {
	vec4 mapValue = texture2D(priorityMap, priorityCoord);
	if(mapValue.a == 0.0) return false;
	
	return priority() < mapValue.x;
}

void generateCoordVertex() {
	vec2 screenCoord = ftransform().xy;
	priorityCoord = 2.0 * screenCoord - 1.0;
}

void generateCoordFragment() {
	// do nothing.
}