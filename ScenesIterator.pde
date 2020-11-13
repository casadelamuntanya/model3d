public abstract class ScenesIterator implements ScenesIterable {
  
  private final ScenesIterable SCENES;
  
  public ScenesIterator(ScenesIterable scenes) {
    SCENES = scenes;
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
  public void draw() {
    SCENES.draw();
  }
}
