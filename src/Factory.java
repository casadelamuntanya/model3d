package ad.casadelamuntanya.model3d;

public interface Factory<T extends Drawable> {
  public Facade<T> load(String source);
}
