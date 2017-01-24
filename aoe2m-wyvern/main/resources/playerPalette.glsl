#version 130
#extension GL_ARB_uniform_buffer_object : require

#include "playerPalette.hdr.glsl"

vec4 paletteQuery(int brightness) {
	return playerPalette_items[(playerIndex * playerPalette_subLength
		 + (brightness & playerPalette_subMask)) & playerPalette_allMask];
}