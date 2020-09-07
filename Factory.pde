public interface Factory<T> {
  public Facade<T> load(String source);
}
