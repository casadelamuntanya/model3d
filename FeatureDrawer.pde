import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Coordinate;

public abstract class FeatureDrawer implements Drawer<Feature> {
  
  public abstract void draw(Feature feature);
  
  protected void drawGeometry(Geometry geometry) {
    if (geometry instanceof GeometryCollection) drawGeometry((GeometryCollection) geometry);
    else if (geometry instanceof Polygon) drawGeometry((Polygon) geometry);
    else if (geometry instanceof LineString) drawGeometry((LineString) geometry);
    else if (geometry instanceof Point) drawGeometry((Point) geometry);
  }
  
  protected void drawGeometry(GeometryCollection geometry) {
    for (int i = 0; i < geometry.getNumGeometries(); i++) {
      drawGeometry(geometry.getGeometryN(i));
    }
  }
  
  protected void drawGeometry(Polygon geometry) {
    LineString shell = geometry.getExteriorRing();
    beginShape();
    for (Coordinate coord : shell.getCoordinates()) vertex((float)coord.x, (float)coord.y);
    for (int i = 0; i < geometry.getNumInteriorRing(); i++) {
      LineString hole = geometry.getInteriorRingN(i);
      beginContour();
      for (Coordinate coord : hole.getCoordinates()) vertex((float)coord.x, (float)coord.y);
      endContour();
    }
    endShape(CLOSE);
  }
  
  protected void drawGeometry(LineString geometry) {
    PVector prev = null;
    for (Coordinate coordinate : geometry.getCoordinates()) {
      if (prev != null) line(prev.x, prev.y, (float)coordinate.x, (float)coordinate.y);
      prev = new PVector((float)coordinate.x, (float)coordinate.y);
    }
  }
  
  protected void drawGeometry(Point geometry) {
    Coordinate coordinate = geometry.getCoordinate();
    point((float)coordinate.x, (float)coordinate.y);
  }
  
}
