package ad.casadelamuntanya.model3d.scene;

import processing.core.PGraphics;

public interface Scene {
  public void draw(PGraphics renderer);
  public void onEnter();
  public void onLeave();
}
