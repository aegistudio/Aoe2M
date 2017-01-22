uniform sampler2D priorityMap;
varying vec2 priorityCoord;

void generateCoordVertex();
void generateCoordFragment();
bool obstructed();