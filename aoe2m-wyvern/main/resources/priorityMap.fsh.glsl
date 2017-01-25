#include "priorityMap.hdr.glsl"
#include "quantization.hdr.glsl"

vec4 priomapVector() {
	return texture2D(priorityMap, priorityCoord);
}

float priomapSample(float level) {
	vec4 mapValue = priomapVector();
	if(mapValue.a < 0.5) return -1.0;
	
	return dequantize(mapValue, level);
}

int priomapCompare(float sampled, float level) {
	vec4 mapValue = priomapVector();
	if(mapValue.a < 0.5) return 1;
	
	return quanCompareFV(sampled, mapValue, level);
}

void priomapFragment() {
	// do nothing.
}