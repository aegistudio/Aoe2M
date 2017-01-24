#version 130

#include "playerPalette.hdr.glsl"

vec4 paletteQuery(int brightness) {
	return playerPalette_items[(playerIndex * playerPalette_subLength
		 + (brightness & playerPalette_subMask)) & playerPalette_allMask];
}