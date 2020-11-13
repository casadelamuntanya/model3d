public class WalkDrawer extends FeatureDrawer {

  protected final FeatureDrawer DRAWER;
  protected final float SPEED;
  protected final int START_TIME;
  
  public WalkDrawer(FeatureDrawer drawer, float speed) {
    DRAWER = drawer;
    SPEED = speed / 1000;
    START_TIME = millis();
  }
  
  @Override
  protected void draw(Polygon geometry) {
    beginShape();
    if (drawLineProgress(geometry.getExteriorRing(), START_TIME)) {
      final float DELAY = START_TIME + (float)geometry.getExteriorRing().getLength() / SPEED;
      for (int i = 0; i < geometry.getNumInteriorRing(); i++) {
        beginContour();
        drawLineProgress(geometry.getInteriorRingN(i), DELAY);
        endContour();
      }
    }
    endShape(CLOSE);
  }
  
  @Override
  protected void draw(LineString geometry) {
    pushStyle();
    noFill();
    beginShape();
    drawLineProgress(geometry, START_TIME);
    endShape();
    popStyle();
  }
  
  private boolean drawLineProgress(LineString ring, float INIT_TIME) {
    final float RUN_DISTANCE = (millis() - INIT_TIME) * SPEED;
    final boolean IS_FINISHED = RUN_DISTANCE > ring.getLength();
    int progress = 0;
    boolean overflow = false;
    PVector prevVertex = null;
    for (Coordinate coord : ring.getCoordinates()) {
      PVector vertex = new PVector((float)coord.x, (float)coord.y);
      if (!IS_FINISHED && prevVertex != null) {
        PVector segment = PVector.sub(vertex, prevVertex);
        progress += segment.mag();
        overflow = progress > RUN_DISTANCE;
        if (overflow) vertex.sub(segment.setMag(progress - RUN_DISTANCE));
      }
      vertex(vertex.x, vertex.y);
      prevVertex = vertex;
      if (overflow) break;
    }
    return IS_FINISHED;
  }
}
