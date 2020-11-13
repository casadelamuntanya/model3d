public class CategoryDrawer<T> implements Drawer<Categorizable> {

  protected final Drawer DRAWER;
  protected final String PROPERTY;
  protected final HashMap<T, Integer> COLORS;
  protected final int STROKE_WEIGHT;
  
  public CategoryDrawer(Drawer drawer, String property, HashMap colors, int strokeWeight) {
    DRAWER = drawer;
    PROPERTY = property;
    COLORS = colors;
    STROKE_WEIGHT = strokeWeight;
  }
  
  public CategoryDrawer(Drawer drawer, String property, HashMap colors) {
    this(drawer, property, colors, 1);
  }
  
  @Override
  public void draw(Categorizable item) {
    pushMatrix();
    pushStyle();
    T key = (T) item.getProperty(PROPERTY);
    if (COLORS.containsKey(key)) {
      fill(COLORS.get(key), STROKE_WEIGHT != 0 ? 200 : 255);
      stroke(COLORS.get(key));
      strokeWeight(STROKE_WEIGHT);
    }
    DRAWER.draw(item);
    popStyle();
    popMatrix();
  }
}
