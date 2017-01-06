uniform sampler2D stickyTexture;
uniform float stickyLbX, stickyLbY;
uniform float stickyRtX, stickyRtY;
varying float locationX, locationY;

void main() {
	if(locationX < stickyLbX || locationY < stickyLbY) { discard; return; }
	if(locationX > stickyRtX || locationY > stickyRtY) { discard; return; }

	float w = stickyRtX - stickyLbX;
	float h = stickyRtY - stickyLbY;
	float u = locationX - stickyLbX;
	float v = locationY - stickyLbY;
	
	vec4 color = texture2D(stickyTexture, vec2(u / w, v / h));
	if(color.a < 0.1) discard;
	else gl_FragColor = color;
}