/**
 * playerPalette.hdr.glsl
 * Aoe2m Wyvern Renderer
 * aegistudio
 *
 * This shader object provides access to player
 * palate. The player palate is a big array with
 * multiple subarrays describing player colors
 * in different brightness.
 */
 
const int playerMaxLength = 256;

uniform int playerIndex;

uniform int playerPalette_allMask;
uniform int playerPalette_subLength;
uniform int playerPalette_subMask;
uniform vec4 playerPalette_items[playerMaxLength];

vec4 paletteQuery(int brightness);
