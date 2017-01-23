/**
 * priorityMap.hdr.glsl
 * Aoe2m Wyvern Renderer
 * aegistudio
 *
 * This shader object provides decomposing 
 * mechanism for a input priority map.
 *
 * Please make sure you invoke *CoordGen
 * before any invocation to prioritySample.
 *
 * Require using header quantization.hdr.glsl.
 */


uniform sampler2D priorityMap;
varying vec2 priorityCoord;

void priomapVertex();
void priomapFragment();

vec4 priomapVector();
float priomapSample(float level);
bool priomapLessThan(float sampled, float level);