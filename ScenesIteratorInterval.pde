import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScenesIteratorInterval extends ScenesIterator {

  private final long INTERVAL;
  private final int KEY_PAUSE_RESUME;
  private ScheduledExecutorService scheduler;
  private boolean isRunning = false;
  private long currentTime = 0;
  private long lastTime = 0;

  public ScenesIteratorInterval(PApplet papplet, long interval, int keyPauseResume, ScenesIterable scenes) {
    super(scenes);
    INTERVAL = interval;
    KEY_PAUSE_RESUME = keyPauseResume;
    papplet.registerMethod("keyEvent", this);
  }

  public void run() {
    isRunning = true;
    lastTime = System.nanoTime();
    final Runnable next = new Runnable() {
      public void run() {
        next();
        lastTime = System.nanoTime();
      }
    };
    scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(next, INTERVAL, INTERVAL, TimeUnit.MILLISECONDS);
  }

  public void stop() {
    isRunning = false;
    scheduler.shutdown();
  }

  public void keyEvent(KeyEvent event) {
    if (event.getAction() == KeyEvent.PRESS) {
      int key = event.getKey() == CODED ? event.getKeyCode() : event.getKey();
      if (key == KEY_PAUSE_RESUME) {
        if (isRunning) stop();
        else run();
      }
    }
  }

  @Override
  public void draw() {
    pushStyle();
    super.draw();
    if (isRunning) currentTime = System.nanoTime();
    long remaining = TimeUnit.MILLISECONDS.convert(currentTime - lastTime, TimeUnit.NANOSECONDS) - INTERVAL;
    fill(#ffffff);
    rect(1500, 1056, map(remaining, 0, INTERVAL, 0, 1157), 2);
    popStyle();
  }
}
