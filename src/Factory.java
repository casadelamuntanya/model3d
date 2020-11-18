package ad.casadelamuntanya.model3d;

public interface Factory<T> {
  public Facade<T> load(String source);
}
