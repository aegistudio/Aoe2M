/**
 * playerPalette.hdr.glsl
 * Aoe2m Wyvern Renderer
 * aegistudio
 *
 * This shader object provides access to player
 * palate. The player palate is a big array with
 * multiple subarrays describing player colors
 * in different brightness.
 *
 * Please specify macro `extension 
 * GL_ARB_uniform_buffer_object : require`, as
 * uniform buffer object (UBO) is in use.
 */
 
const int playerMaxLength = 256;

uniform int playerIndex;

uniform playerPalette {
	int playerPalette_allMask;
	int playerPalette_subLength;
	int playerPalette_subMask;
	vec4 playerPalette_items[playerMaxLength];
};
vec4 paletteQuery(int brightness);
