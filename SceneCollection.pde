public class SceneCollection extends ArrayList<Scene> implements ScenesIterable {
  
  protected int currentScene = 0;

  public int current() {
    return currentScene;
  }

  public void goTo(int target) {
    get(currentScene).onLeave();
    currentScene = (size() + target) % size();
    get(currentScene).onEnter();
  }

  public void prev() {
    goTo(currentScene - 1);
  }

  public void next() {
    goTo(currentScene + 1);
  }

  public void draw() {
    pushStyle();
    get(currentScene).draw();
    textAlign(RIGHT, BOTTOM);
    text(currentScene + 1 + " / " + size(), 1495, 1050);
    popStyle();
  }

}
