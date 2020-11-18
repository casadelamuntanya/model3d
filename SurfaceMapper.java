package ad.casadelamuntanya.model3d.surface;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;

import processing.core.PVector;

import obsa.ad.warp.WarpSurface;

public class SurfaceMapper implements CoordinateSequenceFilter {

  private WarpSurface SURFACE;

  public SurfaceMapper(WarpSurface surface) {
    SURFACE = surface;
  }

  @Override
  public void filter(CoordinateSequence seq, int i) {
    Coordinate coordinate = seq.getCoordinate(i);
    PVector position = SURFACE.mapPoint((float) coordinate.y, (float) coordinate.x);

    // TO DO: If point is outside surface return closest border point
    coordinate.x = position != null ? position.x : i != 0 ? seq.getCoordinate(i-1).x : 0;
    coordinate.y = position != null ? position.y : i != 0 ? seq.getCoordinate(i-1).y : 0;
  }

  @Override
  public boolean isDone() {
    return false;
  }

  @Override
  public boolean isGeometryChanged() {
    return true;
  }

}
