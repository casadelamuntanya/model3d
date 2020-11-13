public static class FeaturePredicates {

  public static Predicate<Feature> filterByProperty(final String property, final String... values) {
    return new Predicate<Feature>() {
      @Override
      public boolean test(Feature feature) {
        for(Object value : values) {
          boolean sameType = feature.PROPERTIES.get(property).getClass() == value.getClass();
          boolean sameValue = feature.PROPERTIES.get(property).toString().equals(value.toString());
          if (sameType && sameValue) return true;
        }
        return false;
      }
    };
  }

}
