class TrackScene implements Scene {
  
  private final PApplet PAPPLET;
  private final Feature TRAIL;
  private final Facade FEATURES;
  
  public TrackScene(PApplet papplet, Feature trail, Facade features) {
    PAPPLET = papplet;
    TRAIL = trail;
    FEATURES = features;
  }
  
  @Override
  public void draw(PGraphics renderer) {
    pushStyle();
    if (FEATURES != null) FEATURES.draw(renderer);
    TRAIL.draw(renderer);
  }

  @Override
  public void onEnter() {
    Drawer progress = new ProgressFeatureDrawer(PAPPLET, 50).head(10);
    TRAIL.setDrawer(new ColorDrawer(progress, #ff0000).strokeWeight(3));
  }
  
  @Override
  public void onLeave() {}
}
