package Question2;

import java.util.*;

public class Dictionary<K, V> {
    private final TreeMap<K, V> dict = new TreeMap<>();


    public boolean containsKey(K key) {
        return dict.containsKey(key);
    }

    public V get(K key) {
        return dict.get(key);
    }

    public void put(K key, V value) {
        dict.put(key, value);
    }

    public Set<K> keys() {
        return dict.keySet();
    }

    public void remove(K key) {
        dict.remove(key);
    }
}
