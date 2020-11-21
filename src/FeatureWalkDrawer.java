package ad.casadelamuntanya.model3d.feature;

import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Coordinate;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class FeatureWalkDrawer extends FeatureDrawer {

  protected final PApplet PAPPLET;
  protected final FeatureDrawer DRAWER;
  protected final float SPEED;
  protected final int START_TIME;
  
  public FeatureWalkDrawer(PApplet papplet, FeatureDrawer drawer, float speed) {
    PAPPLET = papplet;
    DRAWER = drawer;
    SPEED = speed / 1000;
    START_TIME = PAPPLET.millis();
  }
  
  @Override
  protected void draw(PGraphics renderer, Polygon geometry) {
    renderer.beginShape();
    if (drawLineProgress(renderer, geometry.getExteriorRing(), START_TIME)) {
      final float DELAY = START_TIME + (float)geometry.getExteriorRing().getLength() / SPEED;
      for (int i = 0; i < geometry.getNumInteriorRing(); i++) {
        renderer.beginContour();
        drawLineProgress(renderer, geometry.getInteriorRingN(i), DELAY);
        renderer.endContour();
      }
    }
    renderer.endShape(CLOSE);
  }
  
  @Override
  protected void draw(PGraphics renderer, LineString geometry) {
    renderer.pushStyle();
    renderer.noFill();
    renderer.beginShape();
    drawLineProgress(renderer, geometry, START_TIME);
    renderer.endShape();
    renderer.popStyle();
  }
  
  private boolean drawLineProgress(PGraphics renderer, LineString ring, float INIT_TIME) {
    final float RUN_DISTANCE = (PAPPLET.millis() - INIT_TIME) * SPEED;
    final boolean IS_FINISHED = RUN_DISTANCE > ring.getLength();
    double progress = 0;
    boolean overflow = false;
    PVector prevVertex = null;
    for (Coordinate coord : ring.getCoordinates()) {
      PVector vertex = new PVector((float)coord.x, (float)coord.y);
      if (!IS_FINISHED && prevVertex != null) {
        PVector segment = PVector.sub(vertex, prevVertex);
        progress += segment.mag();
        overflow = progress > RUN_DISTANCE;
        if (overflow) vertex.sub(segment.setMag((float) progress - RUN_DISTANCE));
      }
      renderer.vertex(vertex.x, vertex.y);
      prevVertex = vertex;
      if (overflow) break;
    }
    return IS_FINISHED;
  }
}
