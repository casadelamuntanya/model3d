package ad.casadelamuntanya.model3d.scene;

import java.util.ArrayList;
import processing.core.PGraphics;
import processing.core.PConstants;
import ad.casadelamuntanya.model3d.Drawable;
import ad.casadelamuntanya.model3d.Drawer;

public class SceneCollection extends ArrayList<Scene> implements Drawable {

  protected int currentScene = 0;
  protected Drawer drawer;
  protected final ArrayList<SceneIterator> ITERATORS = new ArrayList();

  public int current() {
    return currentScene;
  }
  
  public void setDrawer(Drawer drawer) {
    this.drawer = drawer;
  }

  public Scene goTo(int target) {
    Scene prevScene = this.get(currentScene);
    prevScene.onLeave();
    currentScene = (this.size() + target) % this.size();
    Scene nextScene = this.get(currentScene);
    nextScene.onEnter();
    for (SceneIterator iterator : ITERATORS) {
      iterator.onSceneChange(nextScene, prevScene);
    }
    return nextScene;
  }

  public Scene prev() {
    return goTo(currentScene - 1);
  }

  public Scene next() {
    return goTo(currentScene + 1);
  }

  public void addIterator(SceneIterator iterator) {
    if (!ITERATORS.contains(iterator)) {
      ITERATORS.add(iterator);
      iterator.bind(this);
    }
  }

  public void removeIterator(SceneIterator iterator) {
    if (ITERATORS.contains(iterator)) ITERATORS.remove(iterator);
  }
  
  public void removeIterator(int index) {
    if (ITERATORS.size() > index) ITERATORS.remove(index);
  }

  public void draw(PGraphics renderer) {
    if (drawer != null) drawer.draw(renderer, this); 
    this.get(currentScene).draw(renderer);
    for (SceneIterator iterator : ITERATORS) iterator.draw(renderer);
  }

}
