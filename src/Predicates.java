package ad.casadelamuntanya.model3d;

import java.util.function.Predicate;

public class Predicates {

  public static Predicate<Categorizable> hasProperty(final String property, final Object... values) {
    return new Predicate<Categorizable>() {
      @Override
      public boolean test(Categorizable element) {
        for(Object value : values) {
          boolean sameType = element.getProperty(property).getClass() == value.getClass();
          boolean sameValue = element.getProperty(property).toString().equals(value.toString());
          if (sameType && sameValue) return true;
        }
        return false;
      }
    };
  }

}
