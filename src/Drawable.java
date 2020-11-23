package ad.casadelamuntanya.model3d;

import processing.core.PGraphics;

public interface Drawable {
  public void setDrawer(Drawer drawer);
  public void draw(PGraphics renderer);
}
