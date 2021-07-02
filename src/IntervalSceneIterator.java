package ad.casadelamuntanya.model3d.scene;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.KeyEvent;

public class IntervalSceneIterator extends SceneIterator {

  public final int KEY_PAUSE;
  public final long INTERVAL;
  protected ScheduledExecutorService scheduler;
  protected long startTime = 0;
  protected long stopTime = 0;

  public IntervalSceneIterator(PApplet papplet, int interval, int keyPause) {
    super();
    INTERVAL = interval * 1000;
    KEY_PAUSE = keyPause;
    papplet.registerMethod("keyEvent", this);
  }
  
  public void resume() {
    long delta = (stopTime - startTime);
    long delay = INTERVAL - delta;
    startTime = System.currentTimeMillis() - delta;
    scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        startTime = System.currentTimeMillis();
        if (scenes != null) scenes.next();
      }
    }, delay, INTERVAL, TimeUnit.MILLISECONDS);    
  }
  
  public void pause() {
    scheduler.shutdownNow();
    stopTime = System.currentTimeMillis();
  }
  
  public void toggle() {
    if (isPaused()) resume();
    else pause();
  }
  
  public boolean isPaused() {
    return scheduler == null || scheduler.isShutdown();
  }
  
  public long ellapsed() {
    return isPaused()
      ? stopTime - startTime
      : System.currentTimeMillis() - startTime;
  }
  
  public void keyEvent(KeyEvent event) {
    if (event.getAction() == KeyEvent.PRESS) {
      int key = event.getKey() == CODED ? event.getKeyCode() : event.getKey();
      if (key == KEY_PAUSE) toggle();
    }
  }

}
