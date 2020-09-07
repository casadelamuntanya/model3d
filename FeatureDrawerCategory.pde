import com.vividsolutions.jts.geom.MultiPolygon;

public class FeatureDrawerCategory extends FeatureDrawer {
  
  public final IntDict TINTS;
  protected final String CATEGORY_FIELD;
  protected final int STROKE_WEIGHT;
  
  public FeatureDrawerCategory(String field, IntDict tints) {
    this(field, tints, 1);
  }
  
  public FeatureDrawerCategory(String field, IntDict tints, int strokeWeight) {
    CATEGORY_FIELD = field;
    TINTS = tints;
    STROKE_WEIGHT = strokeWeight;
  }
  
  @Override
  public void draw(Feature feature) {
    pushStyle();
    String key = feature.PROPERTIES.getString(CATEGORY_FIELD);
    int tint = TINTS.hasKey(key) ? TINTS.get(key) : #ffffff;
    fill(tint, 127);
    stroke(tint);
    strokeWeight(STROKE_WEIGHT);
    drawGeometry(feature.GEOMETRY);
    popStyle();
  }
  
}
