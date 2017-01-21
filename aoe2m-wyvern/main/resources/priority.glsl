#include "priority.hdr.glsl"

float priority() {
	float delta = priorityValue - priorityBottom;
	float stride = priorityTop - priorityBottom;
	return delta / stride;
}

bool greater(float priorityMapSample) {
	return priority() > priorityMapSample;
}