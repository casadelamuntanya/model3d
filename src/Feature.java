package ad.casadelamuntanya.model3d.feature;

import processing.core.PGraphics;
import processing.data.JSONObject;
import com.vividsolutions.jts.geom.Geometry;
import ad.casadelamuntanya.model3d.Categorizable;
import ad.casadelamuntanya.model3d.Drawable;
import ad.casadelamuntanya.model3d.Drawer;

public class Feature implements Categorizable, Drawable {
  
  public final int ID;
  public final JSONObject PROPERTIES;
  public final Geometry GEOMETRY;
  protected Drawer drawer;
  
  public Feature(int id, JSONObject properties, Geometry geometry) {
    ID = id;
    PROPERTIES = properties;
    GEOMETRY = geometry;
  }
  
  public boolean contains(Geometry geometry) {
    return GEOMETRY.contains(geometry);
  }
  
  @Override
  public Object getProperty(String property) {
    return PROPERTIES.get(property);
  }
  
  @Override
  public void setDrawer(Drawer drawer) {
    this.drawer = drawer;
  }
  
  @Override
  public void draw(PGraphics renderer) {
    drawer.draw(renderer, GEOMETRY);
  }
  
}
