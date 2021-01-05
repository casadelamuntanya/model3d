package ad.casadelamuntanya.model3d.feature;

import ad.casadelamuntanya.model3d.Drawer;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Coordinate;
import processing.core.PConstants;
import processing.core.PGraphics;

public class FeatureDrawer implements PConstants, Drawer<Feature> {

	public void draw(PGraphics renderer, Feature feature) {
		draw(renderer, feature.GEOMETRY);
	}

  public void draw(PGraphics renderer, Geometry geometry) {
    if (geometry instanceof GeometryCollection) draw(renderer, (GeometryCollection) geometry);
    else if (geometry instanceof Polygon) draw(renderer, (Polygon) geometry);
    else if (geometry instanceof LineString) draw(renderer, (LineString) geometry);
    else if (geometry instanceof Point) draw(renderer, (Point) geometry);
  }

  protected void draw(PGraphics renderer, GeometryCollection geometry) {
    for (int i = 0; i < geometry.getNumGeometries(); i++) {
      draw(renderer, geometry.getGeometryN(i));
    }
  }

  protected void draw(PGraphics renderer, Polygon geometry) {
    LineString shell = geometry.getExteriorRing();
    renderer.beginShape();
    for (Coordinate coord : shell.getCoordinates()) {
      renderer.vertex((float)coord.x, (float)coord.y);
    }
    for (int i = 0; i < geometry.getNumInteriorRing(); i++) {
      LineString hole = geometry.getInteriorRingN(i);
      renderer.beginContour();
      for (Coordinate coord : hole.getCoordinates()) {
        renderer.vertex((float)coord.x, (float)coord.y);
      }
      renderer.endContour();
    }
    renderer.endShape(CLOSE);
  }

  protected void draw(PGraphics renderer, LineString geometry) {
    renderer.beginShape();
    renderer.noFill();
    for (Coordinate coord : geometry.getCoordinates()) {
      renderer.vertex((float)coord.x, (float)coord.y);
    }
    renderer.endShape();
  }

  protected void draw(PGraphics renderer, Point geometry) {
    Coordinate coordinate = geometry.getCoordinate();
    renderer.point((float)coordinate.x, (float)coordinate.y);
  }
}
