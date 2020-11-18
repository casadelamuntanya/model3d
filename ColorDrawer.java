package ad.casadelamuntanya.model3d;

import com.vividsolutions.jts.geom.MultiPolygon;
import processing.core.PGraphics;

public class ColorDrawer<T> implements Drawer<T> {
  
  protected final Drawer DRAWER;
  protected final int COLOR;
  protected final int STROKE_WEIGHT;
  
  public ColorDrawer(Drawer drawer, int paint, int strokeWeight) {
    DRAWER = drawer;
    COLOR = paint;
    STROKE_WEIGHT = strokeWeight;
  }
  
  public ColorDrawer(Drawer drawer, int paint) {
    this(drawer, paint, 1);
  }
  
  @Override
  public void draw(PGraphics renderer, T feature) {
    renderer.pushMatrix();
    renderer.pushStyle();
    renderer.fill(COLOR, STROKE_WEIGHT != 0 ? 200 : 255);
    renderer.stroke(COLOR);
    renderer.strokeWeight(STROKE_WEIGHT);
    DRAWER.draw(renderer, feature);
    renderer.popStyle();
    renderer.popMatrix();
  }
}
