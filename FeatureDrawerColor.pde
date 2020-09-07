import com.vividsolutions.jts.geom.MultiPolygon;

public class FeatureDrawerColor extends FeatureDrawer {

  protected final int FILL;
  protected final int STROKE;
  protected final int STROKE_WEIGHT;

  public FeatureDrawerColor(int fill, int stroke, int strokeWeight) {
    FILL = fill;
    STROKE = stroke;
    STROKE_WEIGHT = strokeWeight;
  }

  public FeatureDrawerColor(int fill) {
    this(fill, fill, 1);
  }

  public FeatureDrawerColor(int fill, int strokeWeight) {
    this(fill, fill, strokeWeight);
  }

  @Override
  public void draw(Feature feature) {
    pushStyle();
    fill(FILL, 127);
    stroke(STROKE);
    strokeWeight(STROKE_WEIGHT);
    drawGeometry(feature.GEOMETRY);
    popStyle();
  }
}
