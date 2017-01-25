#include "quantization.hdr.glsl"

vec4 quantize(float value, float factor) {
	float pv0 = value;
	float pv1 = pv0 * factor;
	float pv2 = pv1 * factor;
	
	pv0 = float(int(pv0 * factor));
	pv0 /= factor;
	
	pv1 = pv1 - float(int(pv1));
	pv2 = pv2 - float(int(pv2));
	
	return vec4(pv0, pv1, pv2, 1.0);
}

float dequantize(vec4 quantized, float factor) {
	return quantized.x + quantized.y / factor + quantized.z / factor / factor;
}

int quanCompare(vec4 left, vec4 right, float factor) {
	int compareRed =  int(left.r * factor) - int(right.r * factor);
	if(compareRed != 0) return compareRed;
	
	int compareGreen =  int(left.g * factor) - int(right.g * factor);
	if(compareGreen != 0) return compareGreen;
	
	int compareBlue =  int(left.b * factor) - int(right.b * factor);
	if(compareBlue != 0) return compareBlue;
	
	return 0;
}

int quanCompareFV(float value, vec4 sampled, float factor) {
	vec4 left = quantize(value, factor);
	return quanCompare(left, sampled, factor);
}