package ad.casadelamuntanya.model3d.scene;

import processing.core.PApplet;
import processing.event.KeyEvent;


public class KeySceneIterator extends SceneIterator {
  
  public final int KEY_PREV;
  public final int KEY_NEXT;
  
  public KeySceneIterator(PApplet papplet) {
    this(papplet, LEFT, RIGHT);
  }

  public KeySceneIterator(PApplet papplet, int keyPrev, int keyNext) {
    super();
    KEY_PREV = keyPrev;
    KEY_NEXT = keyNext;
    papplet.registerMethod("keyEvent", this);
  }

  public void keyEvent(KeyEvent event) {
    if (this.scenes != null && event.getAction() == KeyEvent.PRESS) {
      int key = event.getKey() == CODED ? event.getKeyCode() : event.getKey();
      if (key == KEY_PREV) this.scenes.prev();
      if (key == KEY_NEXT) this.scenes.next();
    }
  }

}
