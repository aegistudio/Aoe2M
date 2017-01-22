/**
 * priority.hdr.glsl
 * Aoe2m Wyvern Renderer
 * aegistudio
 *
 * This shader object provides atomic manipulation
 * on priority. The priority determines which sprite
 * should be rendered first and which later.
 */

uniform float priorityValue;
uniform float priorityBottom, priorityTop;

float priority();