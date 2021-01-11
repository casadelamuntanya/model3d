package ad.casadelamuntanya.model3d.feature;

import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Coordinate;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class ProgressFeatureDrawer extends FeatureDrawer {

  protected final PApplet PAPPLET;
  protected final float SPEED;
  protected final int START_TIME;
  protected int HEAD_SIZE = 0;

  public ProgressFeatureDrawer(PApplet papplet, float speed) {
    PAPPLET = papplet;
    SPEED = speed / 1000;
    START_TIME = PAPPLET.millis();
  }

  public ProgressFeatureDrawer head(int size) {
    HEAD_SIZE = size;
    return this;
  }

  @Override
  protected void draw(PGraphics renderer, Polygon geometry) {
    final float DELAY = START_TIME + (float)geometry.getExteriorRing().getLength() / SPEED;
    renderer.beginShape();
    drawLineProgress(renderer, geometry.getExteriorRing(), START_TIME);
    if (PAPPLET.millis() > DELAY) {
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
    PVector head = drawLineProgress(renderer, geometry, START_TIME);
    renderer.endShape();
    if (HEAD_SIZE > 0 && head != null) {
      renderer.fill(renderer.strokeColor);
      renderer.noStroke();
      renderer.circle(head.x, head.y, HEAD_SIZE);
    }
    renderer.popStyle();
  }

  private PVector drawLineProgress(PGraphics renderer, LineString ring, float INIT_TIME) {
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
    return prevVertex;
  }
}
