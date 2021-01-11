import obsa.ad.warp.*;

WarpSurface surface;
WarpCanvas canvas;

// Canvas bounds
private final LatLon[] bounds = new LatLon[] {
  new LatLon(42.691138, 1.369565),
  new LatLon(42.691138, 1.818963),
  new LatLon(42.398173, 1.818963),
  new LatLon(42.398173, 1.369565)
};
  
void setup() {
  fullScreen(P3D);
  surface = new WarpSurface(this, "../examples/_shared/warpsurface_20x20.xml");
  canvas = new WarpCanvas(this, "../examples/_shared/orto.png", bounds);
}

void draw() {
  background(0);
  surface.draw(canvas);
  
  fill(255);
  text(frameRate, 20, 20);
}
