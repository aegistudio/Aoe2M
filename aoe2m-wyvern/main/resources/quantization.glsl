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