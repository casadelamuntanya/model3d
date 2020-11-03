import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Coordinate;

public class FeatureDrawer implements Drawer<Feature> {

  public void draw(Feature feature) {
    draw(feature.GEOMETRY);
  }

  protected void draw(Geometry geometry) {
    if (geometry instanceof GeometryCollection) draw((GeometryCollection) geometry);
    else if (geometry instanceof Polygon) draw((Polygon) geometry);
    else if (geometry instanceof LineString) draw((LineString) geometry);
    else if (geometry instanceof Point) draw((Point) geometry);
  }
  
  protected void draw(GeometryCollection geometry) {
    for (int i = 0; i < geometry.getNumGeometries(); i++) {
      draw(geometry.getGeometryN(i));
    }
  }
  
  protected void draw(Polygon geometry) {
    LineString shell = geometry.getExteriorRing();
    beginShape();
    for (Coordinate coord : shell.getCoordinates()) {
      vertex((float)coord.x, (float)coord.y);
    }
    for (int i = 0; i < geometry.getNumInteriorRing(); i++) {
      LineString hole = geometry.getInteriorRingN(i);
      beginContour();
      for (Coordinate coord : hole.getCoordinates()) {
        vertex((float)coord.x, (float)coord.y);
      }
      endContour();
    }
    endShape(CLOSE);
  }
  
  protected void draw(LineString geometry) {
    beginShape();
    noFill();
    for (Coordinate coord : geometry.getCoordinates()) {
      vertex((float)coord.x, (float)coord.y);
    }
    endShape();
  }
  
  protected void draw(Point geometry) {
    Coordinate coordinate = geometry.getCoordinate();
    point((float)coordinate.x, (float)coordinate.y);
  }
}
