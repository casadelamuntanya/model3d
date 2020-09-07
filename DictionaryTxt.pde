public class DictionaryTxt extends Dictionary {
  public DictionaryTxt(String source) {
    String[] lines = trim(loadStrings(source));
    for (String line : lines) {
      if (line.length() > 0 && line.charAt(0) != '#') {
        String[] entry = split(line, "=");
        if (entry.length == 2) DICTIONARY.set(trim(entry[0]), trim(entry[1]));
      }
    }
  }
}

