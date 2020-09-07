public class SceneKeyboardIterator implements SceneCollectionIterator {
  
  private final PApplet PAPPLET;
  private final int KEY_PREV;
  private final int KEY_NEXT;
  private SceneCollection collection;
  
  public SceneKeyboardIterator(PApplet papplet, int keyPrev, int keyNext) {
    PAPPLET = papplet;
    KEY_PREV = keyPrev;
    KEY_NEXT = keyNext;
  }
  
  @Override
  public void register(SceneCollection collection) {
    this.collection = collection;
    PAPPLET.registerMethod("keyEvent", this);
  }
  
  public void keyEvent(KeyEvent event) {
    if (event.getAction() == KeyEvent.PRESS) {
      int key = event.getKey() == CODED ? event.getKeyCode() : event.getKey();
      if (key == KEY_PREV) collection.prev();
      if (key == KEY_NEXT) collection.next();
    }
  }

}
