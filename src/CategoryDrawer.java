package ad.casadelamuntanya.model3d;

import java.util.HashMap;
import processing.core.PGraphics;
import ad.casadelamuntanya.model3d.Categorizable;

public class CategoryDrawer<T> implements Drawer<Categorizable> {

  protected final Drawer DRAWER;
  protected final String PROPERTY;
  protected final HashMap<T, Integer> COLORS;
  protected int STROKE_WEIGHT = 1;
  
  public CategoryDrawer(Drawer drawer, String property, HashMap colors) {
    DRAWER = drawer;
    PROPERTY = property;
    COLORS = colors;
  }
  
  public CategoryDrawer strokeWeight(int weight) {
    STROKE_WEIGHT = weight;
    return this;
  }
  
  @Override
  public void draw(PGraphics renderer, Categorizable element) {
    renderer.pushMatrix();
    renderer.pushStyle();
    T key = (T) element.getProperty(PROPERTY);
    if (COLORS.containsKey(key)) {
      renderer.fill(COLORS.get(key), STROKE_WEIGHT != 0 ? 200 : 255);
      renderer.stroke(COLORS.get(key));
      renderer.strokeWeight(STROKE_WEIGHT);
    }
    DRAWER.draw(renderer, element);
    renderer.popStyle();
    renderer.popMatrix();
  }
}
