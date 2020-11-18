package ad.casadelamuntanya.model3d.scene;

import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class ScenesIterator implements PConstants, ScenesIterable {
  
  private final ScenesIterable SCENES;
  
  public ScenesIterator(ScenesIterable scenes) {
    SCENES = scenes;
  }
  
  @Override
  public void init() {
    SCENES.init();
  }
  
  @Override
  public int current() {
    return SCENES.current();
  }
  
  @Override
  public void goTo(int target) {
    SCENES.goTo(target);
  }
  
  @Override
  public void prev() {
    SCENES.prev();
  }
  
  @Override
  public void next() {
    SCENES.next();
  }
  
  @Override
  public void draw(PGraphics renderer) {
    SCENES.draw(renderer);
  }
}
