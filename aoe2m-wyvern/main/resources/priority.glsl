uniform float priority;
uniform float priorityBottom, priorityTop;

float priority() {
	float delta = priority - priorityBottom;
	float stride = priorityTop - priorityBottom;
	return delta / stride;
}

bool greater(float priorityMapSample) {
	return priority() > priorityMapSample;
}