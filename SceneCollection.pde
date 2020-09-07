public class SceneCollection implements Scene {
  
  private final ArrayList<Scene> SCENES = new ArrayList();
  private int currentScene = 0;

  public SceneCollection(Scene... scenes) {
    for (Scene scene : scenes) SCENES.add(scene);
  }
  
  public int size() {
    return SCENES.size();
  }
  
  public int current() {
    return currentScene;
  }
  
  public boolean add(Scene scene) {
    return SCENES.add(scene);
  }
  
  public void goTo(int targetScene) {  
    currentScene = (SCENES.size() + targetScene) % SCENES.size();
  }
  
  public void next() {
    goTo(currentScene + 1);
  }
  
  public void prev() {
    goTo(currentScene - 1);
  }
  
  public void addIterator(SceneCollectionIterator iterator) {
    iterator.register(this);
  }
  
  @Override
  public void draw() {
    pushStyle();
    
    SCENES.get(currentScene).draw();
    
    popStyle();
  }

}
