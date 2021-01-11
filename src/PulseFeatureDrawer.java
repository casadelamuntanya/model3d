package ad.casadelamuntanya.model3d.feature;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.LineString;
import processing.core.PApplet;
import processing.core.PGraphics;

public class PulseFeatureDrawer extends FeatureDrawer {

  protected final PApplet PAPPLET;
  protected final int SIZE;
  protected final int RADIUS;
  protected float SPEED = 0.025f;
  protected float DUTY_CYCLE = 0.5f;
  protected final int COLOR;

  public PulseFeatureDrawer(PApplet papplet, int size, int radius, int paint) {
    PAPPLET = papplet;
    SIZE = size;
    RADIUS = radius;
    COLOR = paint;
  }

  public PulseFeatureDrawer speed(float speed) {
    SPEED = speed;
    return this;
  }

  public PulseFeatureDrawer dutyCycle(float dutyCycle) {
    DUTY_CYCLE = dutyCycle;
    return this;
  }

  public void draw(PGraphics renderer, Point geometry) {
    Coordinate coordinate = geometry.getCoordinate();
    renderer.pushStyle();
    renderer.pushMatrix();
    renderer.noStroke();
    drawPulse(renderer, (float)coordinate.x, (float)coordinate.y, 0);
    drawPulse(renderer, (float)coordinate.x, (float)coordinate.y, RADIUS / 3);
    renderer.fill(COLOR);
    renderer.circle((float)coordinate.x, (float)coordinate.y, SIZE);
    renderer.popMatrix();
    renderer.popStyle();
  }

  private void drawPulse(PGraphics renderer, float x, float y, float offset) {
    float radius = (PAPPLET.millis() * SPEED - offset) % (RADIUS / DUTY_CYCLE);
    float opacity = PApplet.map(radius, 0, RADIUS, 255, 0);
    renderer.fill(COLOR, opacity);
    renderer.circle(x, y, radius);
  }

}
