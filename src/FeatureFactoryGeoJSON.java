package ad.casadelamuntanya.model3d.feature;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;
import ad.casadelamuntanya.model3d.Factory;
import ad.casadelamuntanya.model3d.Facade;

public class FeatureFactoryGeoJSON implements Factory<Feature> {

  private final PApplet PAPPLET;
  private final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();
  private final CoordinateSequenceFilter MAPPER;
  private int counter = 0;
  
  public FeatureFactoryGeoJSON(PApplet papplet, CoordinateSequenceFilter mapper) {
    PAPPLET = papplet;
    MAPPER = mapper;
  }
  
  public Facade<Feature> load(String source) {
    Facade<Feature> facade = new Facade();
    JSONObject json = PAPPLET.loadJSONObject(source);
    JSONArray features = json.getJSONArray("features");
    for (int i = 0; i < features.size(); i++) {
      JSONObject feature = features.getJSONObject(i);
      JSONObject properties = feature.getJSONObject("properties");
      Geometry geometry = createGeometry(feature.getJSONObject("geometry"));
      if (geometry != null) {
        geometry.apply(MAPPER);
        facade.add(new Feature(counter, properties, geometry));
        counter += 1;
      }
    }
    return facade;
  }
  
  protected Geometry createGeometry(JSONObject geometry) {
      JSONArray coordinates = geometry.getJSONArray("coordinates");
      GeometryType type = GeometryType.valueOf(geometry.getString("type"));
      switch (type) {
        case MultiPolygon: return createMultiPolygon(coordinates);
        case Polygon: return createPolygon(coordinates);
        case MultiLineString: return createMultiLineString(coordinates);
        case LineString: return createLineString(coordinates);
        case Point: return createPoint(coordinates);
        default: return null;
      }
  }
  
  protected MultiPolygon createMultiPolygon(JSONArray multiPolygon) {
    Polygon[] polygons = new Polygon[multiPolygon.size()];
    for (int i = 0; i < multiPolygon.size(); i++) {
      polygons[i] = createPolygon(multiPolygon.getJSONArray(i));
    }
    return GEOMETRY_FACTORY.createMultiPolygon(polygons);
  }

  protected Polygon createPolygon(JSONArray polygon) {
    LinearRing shell = createLinearRing(polygon.getJSONArray(0));
    if (polygon.size() > 1) {
      LinearRing[] holes = new LinearRing[polygon.size() - 1];
      for (int i = 1; i < polygon.size(); i++) {
        holes[i - 1] = createLinearRing(polygon.getJSONArray(i));
      }
      return GEOMETRY_FACTORY.createPolygon(shell, holes);
    }
    return  GEOMETRY_FACTORY.createPolygon(shell);
  }

  protected LinearRing createLinearRing(JSONArray linearRing) {
    Coordinate[] coordinates = new Coordinate[linearRing.size()];
    for (int i = 0; i < linearRing.size(); i++) {
      JSONArray coordinate = linearRing.getJSONArray(i);
      float lon = coordinate.getFloat(0);
      float lat = coordinate.getFloat(1);
      coordinates[i] = new Coordinate(lon, lat);
    }
    return GEOMETRY_FACTORY.createLinearRing(coordinates);
  }
  
  protected MultiLineString createMultiLineString(JSONArray multiLineString) {
    LineString[] lines = new LineString[multiLineString.size()];
    for (int i = 0; i < multiLineString.size(); i++) {
      lines[i] = createLineString(multiLineString.getJSONArray(i));
    }
    return GEOMETRY_FACTORY.createMultiLineString(lines);
  }
  
  
  protected LineString createLineString(JSONArray lineString) {
    Coordinate[] coordinates = new Coordinate[lineString.size()];
    for (int i = 0; i < lineString.size(); i++) {
      JSONArray coordinate = lineString.getJSONArray(i);
      float lon = coordinate.getFloat(0);
      float lat = coordinate.getFloat(1);
      coordinates[i] = new Coordinate(lon, lat);
    }
    return GEOMETRY_FACTORY.createLineString(coordinates);
  }
  
  protected MultiPoint createMultiPoint(JSONArray multiPoint) {
    Point[] points = new Point[multiPoint.size()];
    for (int i = 0; i < multiPoint.size(); i++) {
      points[i] = createPoint(multiPoint.getJSONArray(i));
    }
    return GEOMETRY_FACTORY.createMultiPoint(points);
  }
  
  protected Point createPoint(JSONArray point) {
    float lon = point.getFloat(0);
    float lat = point.getFloat(1);
    Coordinate coordinate = new Coordinate(lon, lat);
    return GEOMETRY_FACTORY.createPoint(coordinate);
  }

}
