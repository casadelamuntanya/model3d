package ad.casadelamuntanya.model3d.feature;

import processing.data.JSONObject;
import com.vividsolutions.jts.geom.Geometry;
import ad.casadelamuntanya.model3d.Categorizable;

public class Feature implements Categorizable {
  
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
  
  public Object getProperty(String property) {
    return PROPERTIES.get(property);
  }
}
