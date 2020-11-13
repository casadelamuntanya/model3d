public class Dictionary {
  private final HashMap<String, StringDict> DICTIONARIES;
  private String defaultLanguage;
  private DictionaryFactory factory;
  
  public Dictionary(DictionaryFactory factory) {
    DICTIONARIES = new HashMap<String, StringDict>();
    setFactory(factory);
  }
  
  public Dictionary setDefault(String language) {
    defaultLanguage = language;
    return this;
  }
  
  public Dictionary setFactory(DictionaryFactory factory) {
    this.factory = factory;
    return this;
  }
  
  public Dictionary load(String language, String path) {
    DICTIONARIES.put(language, factory.load(path));
    if (defaultLanguage == null) setDefault(language);
    return this;
  }

  public String get(String key) {
    return get(key, defaultLanguage);
  }
  
  public String get(String key, String language) {
    return DICTIONARIES.containsKey(language) && DICTIONARIES.get(language).hasKey(key)
      ? DICTIONARIES.get(language).get(key)
      : language + "." + key;
  }
}
