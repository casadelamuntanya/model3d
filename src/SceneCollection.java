package ad.casadelamuntanya.model3d.scene;

import java.util.ArrayList;
import processing.core.PGraphics;
import processing.core.PConstants;

public class SceneCollection extends ArrayList<Scene> implements PConstants, ScenesIterable {
  
  protected int currentScene = 0;

  public int current() {
    return currentScene;
  }

  @Override
  public void init() {
    this.get(currentScene).onEnter();
  }

  @Override
  public void goTo(int target) {
    this.get(currentScene).onLeave();
    currentScene = (size() + target) % size();
    this.get(currentScene).onEnter();
  }

  @Override
  public void prev() {
    goTo(currentScene - 1);
  }

  @Override
  public void next() {
    goTo(currentScene + 1);
  }

  @Override
  public void draw(PGraphics renderer) {
    renderer.pushStyle();
    this.get(currentScene).draw(renderer);
    renderer.textAlign(RIGHT, BOTTOM);
    renderer.text(currentScene + 1 + " / " + size(), 1495, 1050);
    renderer.popStyle();
  }

}
