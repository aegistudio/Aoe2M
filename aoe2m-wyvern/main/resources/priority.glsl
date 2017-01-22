#include "priority.hdr.glsl"

float priority() {
	float delta = priorityValue - priorityBottom;
	float stride = priorityTop - priorityBottom;
	return 1.0 * delta / stride;
}