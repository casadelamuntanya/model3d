import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SceneIntervalIterator implements SceneCollectionIterator {

  private final long INTERVAL;
  private ScheduledExecutorService scheduler;
  private SceneCollection collection;
  private boolean isRunning = false;
  private long time = 0;
  
  public SceneIntervalIterator(long milliseconds) {
    INTERVAL = milliseconds;
  }
  
  public void register(SceneCollection collection) {
    this.collection = collection;
  }
  
  public void run() {
    if (!isRunning()) {
      isRunning = true;
      time = System.nanoTime();
      final Runnable next = new Runnable() {
        public void run() {
          collection.next();
          time = System.nanoTime();
        }
      };
      scheduler = Executors.newScheduledThreadPool(1);
      scheduler.scheduleAtFixedRate(next, INTERVAL, INTERVAL, TimeUnit.MILLISECONDS);
    }
  }
   
  public void stop() {
    if (isRunning()) {
      isRunning = false;
      scheduler.shutdown();
    }
  }
  
  public boolean isRunning() {
    return isRunning;
  }
  
  public long remaining() {
    if (!isRunning) return 0;
    long elapsed = System.nanoTime() - time;
    return INTERVAL - TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS);
  }
   
}
