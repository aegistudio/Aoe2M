/**
 * quantization.hdr.glsl
 * Aoe2m Wyvern Renderer
 * aegistudio
 *
 * This shader object provides quantization
 * mechanism that encode a float value into
 * a three (not four) word vector, and its
 * reverse process.
 */

const float defaultFactor = 64.0;

vec4 quantize(float value, float factor);

float dequantize(vec4 quantized, float factor);

bool quanLessThan(float value, vec4 sampled, float factor);