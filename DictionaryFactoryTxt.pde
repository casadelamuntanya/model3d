public class DictionaryFactoryTxt implements DictionaryFactory {
  public StringDict load(String source) {
    StringDict dictionary = new StringDict();
    String[] lines = trim(loadStrings(source));
    for (String line : lines) {
      if (line.length() > 0 && line.charAt(0) != '#') {
        String[] entry = split(line, "=");
        if (entry.length == 2) dictionary.set(trim(entry[0]), trim(entry[1]));
      }
    }
    return dictionary;
  }
}
