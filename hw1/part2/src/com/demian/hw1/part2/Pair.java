package com.demian.hw1.part2;

/*
  This class is quite self-explanatory.
  I need it because java standard library has no similar class (not counting javafx.util.Pair)
 */
public class Pair<K, V> {
  private K key;
  private V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }
}
