package ad.casadelamuntanya.model3d.scene;

import processing.core.PGraphics;

public interface ScenesIterable {
  public void init();
  public int current();
  public void goTo(int target);
  public void prev();
  public void next();
  public void draw(PGraphics renderer);
}
