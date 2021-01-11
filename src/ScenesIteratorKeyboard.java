package ad.casadelamuntanya.model3d.scene;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class ScenesIteratorKeyboard extends ScenesIterator {

  private final int KEY_PREV;
  private final int KEY_NEXT;

  public ScenesIteratorKeyboard(PApplet papplet, int keyPrev, int keyNext, ScenesIterable scenes) {
    super(scenes);
    KEY_PREV = keyPrev;
    KEY_NEXT = keyNext;
    papplet.registerMethod("keyEvent", this);
  }

  public void keyEvent(KeyEvent event) {
    if (event.getAction() == KeyEvent.PRESS) {
      int key = event.getKey() == CODED ? event.getKeyCode() : event.getKey();
      if (key == KEY_PREV) prev();
      if (key == KEY_NEXT) next();
    }
  }
}
