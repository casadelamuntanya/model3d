public abstract class Dictionary {
  protected final StringDict DICTIONARY = new StringDict();
  
  public String get(String key) {
    return DICTIONARY.hasKey(key) ? DICTIONARY.get(key) : key;
  }
}
