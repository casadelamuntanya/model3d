import obsa.ad.warp.*;
import ad.casadelamuntanya.model3d.*;
import ad.casadelamuntanya.model3d.feature.*;
import ad.casadelamuntanya.model3d.surface.*;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;

Facade<Feature> borders;
Facade<Feature> landuses;

WarpSurface surface;
WarpCanvas canvas;

// Canvas bounds
private final LatLon[] bounds = new LatLon[] {
  new LatLon(42.691138, 1.369565),
  new LatLon(42.691138, 1.818963),
  new LatLon(42.398173, 1.818963),
  new LatLon(42.398173, 1.369565)
};
  
void setup() {
  fullScreen(P3D);
  surface = new WarpSurface(this, "../data/warpsurface_20x20.xml");
  canvas = new WarpCanvas(this, "../data/orto.png", bounds);
  
  SurfaceMapper mapper = new SurfaceMapper(surface);
  
  Factory factory = new FeatureFactoryGeoJSON(this, mapper);
  
  HashMap<String, Integer> colors = new HashMap();
  colors.put("BRUSH", #617B35);
  colors.put("BARE_SOIL", #bbbbbb);
  colors.put("CROP", #ddd771);
  colors.put("LAKE", #89cff0);
  colors.put("RIVER", #89cff0);
  colors.put("FOREST_CLEAR", #617B35);
  colors.put("FOREST_DENSE", #617B35);
  colors.put("MEADOW", #bdc07e);
  colors.put("ROAD_PRIMARY", color(#ff0000, 150));
  colors.put("ROAD_SECONDARY", color(#ff0000, 125));
  colors.put("ROAD_TERTIARY", color(#ff0000, 100));
  colors.put("URBAN", #ff0000);
  colors.put("ROCK", #999999);
  colors.put("SCREE", #999999);
  
  landuses = factory.load("../data/landuses.geojson");
  landuses.setDrawer(new CategoryDrawer(new FeatureDrawer(), "sub_type", colors));
  
  borders = factory.load("../data/border.geojson");
  borders.setDrawer(new ColorDrawer(new FeatureDrawer(), #ffffff));
  
}

void draw() {
  background(0);
  surface.draw(canvas);
  landuses.draw(this.getGraphics());
  borders.draw(this.getGraphics());
}
