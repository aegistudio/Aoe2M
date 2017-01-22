#include "priorityMap.hdr.glsl"
#include "quantization.hdr.glsl"

float priomapSample(float level) {
	vec4 mapValue = texture2D(priorityMap, priorityCoord);
	if(mapValue.a == 0.0) return -1;
	
	return dequantize(mapValue, level);
}

void priomapFragment() {
	// do nothing.
}