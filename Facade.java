package ad.casadelamuntanya.model3d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;
import processing.core.PGraphics;

public class Facade<T> implements Iterable<T> {

  protected final ArrayList<T> ITEMS = new ArrayList<T>();
  protected Drawer<T> drawer;
  
  public void setDrawer(Drawer<T> drawer) {
    this.drawer = drawer;
  }
  
  public int size() {
    return ITEMS.size();
  }

  public void add(T... items) {
    for (T item : items) ITEMS.add(item);
  }
  
  public void add(Facade<T> facade) {
    for (T item : facade) ITEMS.add(item);
  }
  
  public void remove(T... items) {
    for (T item : items) ITEMS.remove(item);
  }
  
  public Facade<T> filter(Predicate filter) {
    Facade<T> facade = new Facade<T>();
    facade.setDrawer(drawer);
    for (T item : ITEMS) {
      if (filter.test(item)) facade.add(item);
    }
    return facade;
  }

  public T find(Predicate finder) {
    for (T item : ITEMS) {
      if (finder.test(item)) return item;
    }
    return null;
  }
  
  public void sort(Comparator<T> comparator) {
    Collections.sort(ITEMS, comparator);
  }
  
  public void draw(PGraphics renderer) {
    if (drawer != null) {
      for (T item : ITEMS) drawer.draw(renderer, item);
    }
  }
  
  @Override
  public Iterator<T> iterator() {
    Iterator<T> iterator = ITEMS.iterator();
    return iterator;
  }

}
