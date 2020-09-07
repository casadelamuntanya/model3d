import com.vividsolutions.jts.geom.Geometry;

public class Feature {
  
  public final int ID;
  public final JSONObject PROPERTIES;
  public final Geometry GEOMETRY;
  
  public Feature(int id, JSONObject properties, Geometry geometry) {
    ID = id;
    PROPERTIES = properties;
    GEOMETRY = geometry;
  }
  
  public boolean contains(Geometry geometry) {
    return GEOMETRY.contains(geometry);
  }
}
