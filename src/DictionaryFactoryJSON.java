package ad.casadelamuntanya.model3d.locale;

import java.util.Iterator;
import processing.core.PApplet;
import processing.data.StringDict;
import processing.data.JSONObject;

public class DictionaryFactoryJSON implements DictionaryFactory {
  
  protected final PApplet PAPPLET;
  
  public DictionaryFactoryJSON(PApplet papplet) {
    PAPPLET = papplet;
  }
  
  public StringDict load(String source) {
    StringDict dictionary = new StringDict(); 
    JSONObject json = PAPPLET.loadJSONObject(source);
    Iterator<String> keys = json.keyIterator();
    while (keys.hasNext()) {
      String key = keys.next();
      dictionary.set(key, json.getString(key));
    }
    return dictionary;
  }
}
