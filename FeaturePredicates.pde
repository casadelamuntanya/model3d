public static class FeaturePredicates {

  public static Predicate<Feature> filter(final String property, final String... options) {
    return new Predicate<Feature>() {
      @Override
      public boolean test(Feature feature) {
        for (String category : options) {
          if (feature.PROPERTIES.getString(property).equals(category)) return true;
        }
        return false;
      }
    };
  }

}
