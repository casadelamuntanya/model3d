import com.vividsolutions.jts.geom.MultiPolygon;

public class ColorDrawer<T> implements Drawer<T> {
  
  protected final Drawer DRAWER;
  protected final color COLOR;
  protected final int STROKE_WEIGHT;
  
  public ColorDrawer(Drawer drawer, color paint, int strokeWeight) {
    DRAWER = drawer;
    COLOR = paint;
    STROKE_WEIGHT = strokeWeight;
  }
  
  public ColorDrawer(Drawer drawer, color paint) {
    this(drawer, paint, 1);
  }
  
  @Override
  public void draw(T feature) {
    pushMatrix();
    pushStyle();
    fill(COLOR, STROKE_WEIGHT != 0 ? 200 : 255);
    stroke(COLOR);
    strokeWeight(STROKE_WEIGHT);
    DRAWER.draw(feature);
    popStyle();
    popMatrix();
  }
}
