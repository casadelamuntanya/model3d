package ad.casadelamuntanya.model3d.locale;

import processing.core.PApplet;
import processing.data.StringDict;

public class DictionaryFactoryTxt implements DictionaryFactory {
  
  protected final PApplet PAPPLET;
  
  public DictionaryFactoryTxt(PApplet papplet) {
    PAPPLET = papplet;
  }
  
  public StringDict load(String source) {
    StringDict dictionary = new StringDict();
    String[] lines = PApplet.trim(PAPPLET.loadStrings(source));
    for (String line : lines) {
      if (line.length() > 0 && line.charAt(0) != '#') {
        String[] entry = PApplet.split(line, "=");
        if (entry.length == 2) dictionary.set(PApplet.trim(entry[0]), PApplet.trim(entry[1]));
      }
    }
    return dictionary;
  }
}
