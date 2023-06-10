package org.example;

import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList {

  private static final int DEFAULT_INITIAL_CAPACITY = 10;


  private Integer[] data;
  private int size;

  public StringListImpl() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  public StringListImpl(int initialCapacity) {
    this.data = new Integer[initialCapacity];
    this.size = 0;
  }

  @Override
  public Integer add(Integer item) {
    return add(size, item);
  }

  @Override
  public Integer add(int index, Integer item) {
    checkItem(item);
    checkIndex(index, true);
    if (size >= data.length) {
      grow();
    }
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

  private void grow() {
    Integer[] data = new Integer[(int) (this.data.length * 1.5)];
    System.arraycopy(this.data, 0, data, 0, this.data.length);
    this.data = data;
  }

  @Override
  public Integer set(int index, Integer item) {
    checkItem(item);
    checkIndex(index, false);
    return data[index] = item;
  }

  @Override
  public Integer remove(Integer item) {
    checkItem(item);
    int indexForRemoving = indexOf(item);
    return remove(indexForRemoving);
  }

  @Override
  public Integer remove(int index) {
    checkIndex(index, false);
    Integer removed = data[index];
    if (index < size - 1) {
      System.arraycopy(data, index + 1, data, index, size - index - 1);
    }
    data[--size] = null;
    return removed;
  }

  @Override
  public boolean contains(Integer item) {
    checkItem(item);

    Integer[] copy = toArray();
    quickSort(copy);

    int min = 0;
    int max = copy.length - 1;
    while (min <= max) {
      int mid = (min + max) / 2;
      if (item.equals(copy[mid])) {
        return true;
      }
      if (item < copy[mid]) {
        max = mid - 1;
      } else {
        min = mid + 1;
      }
    }
    return false;
  }

  private void quickSort(Integer[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private void quickSort(Integer[] arr, int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(arr, begin, end);

      quickSort(arr, begin, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
  }

  private int partition(Integer[] arr, int begin, int end) {
    Integer pivot = arr[end];
    int i = (begin - 1);

    for (int j = begin; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }

    swap(arr, i + 1, end);
    return i + 1;
  }

  private void swap(Integer[] arr, int left, int right) {
    Integer temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
  }

  @Override
  public int indexOf(Integer item) {
    checkItem(item);
    for (int i = 0; i < size; i++) {
      if (Objects.equals(item, data[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Integer item) {
    checkItem(item);
    for (int i = size - 1; i >= 0; i--) {
      if (Objects.equals(item, data[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public Integer get(int index) {
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
  public Integer[] toArray() {
    Integer[] result = new Integer[size];
    System.arraycopy(data, 0, result, 0, size);
    return result;
  }

  private void checkItem(Integer item) {
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
