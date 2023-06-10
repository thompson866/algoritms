package org.example;

import java.util.Objects;

public class StringListImpl implements StringList {

  private static final int DEFAULT_INITIAL_CAPACITY = 10;


  private final String[] data;
  private int size;

  public StringListImpl() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  public StringListImpl(int initialCapacity) {
    this.data = new String[initialCapacity];
    this.size = 0;
  }

  @Override
  public String add(String item) {
    return add(size, item);
  }

  @Override
  public String add(int index, String item) {
    checkItem(item);
    checkIndex(index, true);
    if (size < data.length) {
      if (index < size) {
        System.arraycopy(data, index, data, index + 1, size - index);
      }
      size++;
      return data[index] = item;
    } else {
      throw new IllegalArgumentException("Список полон!");
    }
  }

  @Override
  public String set(int index, String item) {
    checkItem(item);
    checkIndex(index, false);
    return data[index] = item;
  }

  @Override
  public String remove(String item) {
    checkItem(item);
    int indexForRemoving = indexOf(item);
    return remove(indexForRemoving);
  }

  @Override
  public String remove(int index) {
    checkIndex(index, false);
    String removed = data[index];
    if (index < size - 1) {
      System.arraycopy(data, index + 1, data, index, size - index - 1);
    }
    data[--size] = null;
    return removed;
  }

  @Override
  public boolean contains(String item) {
    checkItem(item);
    return indexOf(item) != -1;
  }

  @Override
  public int indexOf(String item) {
    checkItem(item);
    for (int i = 0; i < size; i++) {
      if (Objects.equals(item, data[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(String item) {
    checkItem(item);
    for (int i = size - 1; i >= 0; i--) {
      if (Objects.equals(item, data[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public String get(int index) {
    checkIndex(index, false);
    return data[index];
  }

  @Override
  public boolean equals(StringList otherList) {
    if (size != otherList.size()) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      if (!Objects.equals(get(i), otherList.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      data[i] = null;
    }
    size = 0;
  }

  @Override
  public String[] toArray() {
    String[] result = new String[size];
    System.arraycopy(data, 0, result, 0, size);
    return result;
  }

  private void checkItem(String item) {
    if (item == null) {
      throw new IllegalArgumentException("item не может быть null!");
    }
  }

  private void checkIndex(int index, boolean include) {
    boolean checkUpperBound = include ? index <= size : index < size;
    if (index < 0 || !checkUpperBound) {
      throw new IllegalArgumentException("Выход индекса за границы!");
    }
  }

}
