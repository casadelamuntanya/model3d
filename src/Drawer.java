package ad.casadelamuntanya.model3d;

import processing.core.PGraphics;

public interface Drawer<T> {
  public void draw(PGraphics renderer, T element);
}
