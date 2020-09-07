import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class Facade<T> implements Iterable<T> {

  protected final ArrayList<T> ITEMS = new ArrayList<T>();
  protected final Factory FACTORY;
  protected Drawer<T> drawer;

  public Facade(Factory factory) {
    FACTORY = factory;
  }
  
  public void setDrawer(Drawer<T> drawer) {
    this.drawer = drawer;
  }
  
  public int size() {
    return ITEMS.size();
  }

  public void add(T... features) {
    for (T feature : features) ITEMS.add(feature);
  }
  
  public void add(Facade<T> facade) {
    for (T item : facade) ITEMS.add(item);
  }
  
  public void remove(T... features) {
    for (T feature : features) ITEMS.remove(feature);
  }
  
  public Facade<T> filter(Predicate<T> filter) {
    Facade<T> facade = new Facade<T>(FACTORY);
    facade.setDrawer(drawer);
    for (T item : ITEMS) {
      if (filter.test(item)) facade.add(item);
    }
    return facade;
  }


  public T find(Predicate<T> finder) {
    for (T item : ITEMS) {
      if (finder.test(item)) return item;
    }
    return null;
  }
  
  public void sort(Comparator<T> comparator) {
    Collections.sort(ITEMS, comparator);
  }
  
  public void draw() {
    if (drawer != null) {
      for (T item : ITEMS) drawer.draw(item);
    }
  }
  
  @Override
  public Iterator<T> iterator() {
    Iterator<T> iterator = ITEMS.iterator();
    return iterator;
  }

}
