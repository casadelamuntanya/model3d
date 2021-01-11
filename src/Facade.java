package ad.casadelamuntanya.model3d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;
import processing.core.PGraphics;

public class Facade<T extends Drawable> implements Iterable<T>, Drawable {

  protected final ArrayList<T> ITEMS = new ArrayList<T>();
  
  public int size() {
    return ITEMS.size();
  }

  public Facade<T> add(T... items) {
    for (T item : items) ITEMS.add(item);
		return this;
  }
  
  public Facade<T> add(Facade<T> facade) {
    for (T item : facade) ITEMS.add(item);
		return this;
  }
  
  public Facade<T> remove(T... items) {
    for (T item : items) ITEMS.remove(item);
		return this;
  }
  
  public Facade<T> filter(Predicate filter) {
    Facade<T> facade = new Facade<T>();
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
  
  public Facade<T> sort(Comparator comparator) {
    Collections.sort(ITEMS, comparator);
		return this;
  }
  
  @Override
  public Iterator<T> iterator() {
    Iterator<T> iterator = ITEMS.iterator();
    return iterator;
  }
  
  @Override
  public void setDrawer(Drawer drawer) {
    for (T item : ITEMS) item.setDrawer(drawer);
  }
  
  public void setDrawer(Drawer drawer, Predicate filter) {
    this.filter(filter).setDrawer(drawer);
  }
  
  @Override
  public void draw(PGraphics renderer) {
    for (T item : ITEMS) item.draw(renderer);
  }

}
