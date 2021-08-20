package ad.casadelamuntanya.model3d.scene;

import processing.core.PConstants;
import processing.core.PGraphics;
import ad.casadelamuntanya.model3d.Drawable;
import ad.casadelamuntanya.model3d.Drawer;

public abstract class SceneIterator implements PConstants, Drawable {

  protected SceneCollection scenes;
  protected Drawer drawer;
  
  public void bind(SceneCollection scenes) {
    if (this.scenes == null) {
      this.scenes = scenes;
      this.scenes.addIterator(this);
    }
  }
  
  @Override
  public void setDrawer(Drawer drawer) {
    this.drawer = drawer;
  }
  
  @Override
  public void draw(PGraphics renderer) {
    if (drawer != null) drawer.draw(renderer, this);
  };
  
  public void onSceneChange(Scene next, Scene prev) {};
};
