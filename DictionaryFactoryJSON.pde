public class DictionaryFactoryJSON implements DictionaryFactory {
  public StringDict load(String source) {
    StringDict dictionary = new StringDict(); 
    JSONObject json = loadJSONObject(source);
    Iterator<String> keys = json.keyIterator();
    while (keys.hasNext()) {
      String key = keys.next();
      dictionary.set(key, json.getString(key));
    }
    return dictionary;
  }
}
