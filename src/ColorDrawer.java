package ad.casadelamuntanya.model3d;

import com.vividsolutions.jts.geom.MultiPolygon;
import processing.core.PGraphics;

public class ColorDrawer<T> implements Drawer<T> {
  
  protected final Drawer DRAWER;
  protected final int COLOR;
  protected int STROKE_WEIGHT = 1;
  
  public ColorDrawer(Drawer drawer, int paint) {
    DRAWER = drawer;
    COLOR = paint;
  }
  
  public ColorDrawer strokeWeight(int weight) {
    STROKE_WEIGHT = weight;
    return this;
  }
  
  @Override
  public void draw(PGraphics renderer, T element) {
    renderer.pushMatrix();
    renderer.pushStyle();
    renderer.fill(COLOR, STROKE_WEIGHT != 0 ? 200 : 255);
    renderer.stroke(COLOR);
    renderer.strokeWeight(STROKE_WEIGHT);
    DRAWER.draw(renderer, element);
    renderer.popStyle();
    renderer.popMatrix();
  }
}
