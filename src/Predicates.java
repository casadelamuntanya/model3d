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
  
  public static Predicate and(final Predicate... predicates) {
    return new Predicate() {
      @Override
      public boolean test(Object obj) {
        for (Predicate predicate : predicates) {
          if (!predicate.test(obj)) return false;
        }
        return true;
      }
    };
  }
  
  public static Predicate or(final Predicate... predicates) {
    return new Predicate() {
      @Override
      public boolean test(Object obj) {
        for (Predicate predicate : predicates) {
          if (predicate.test(obj)) return true;
        }
        return false;
      }
    };
  }
  
  public static Predicate not(final Predicate predicate) {
    return new Predicate() {
      @Override
      public boolean test(Object obj) {
        return !predicate.test(obj);
      }
    };
  }

}
