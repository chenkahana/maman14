package Question1;

import java.util.ArrayList;
import java.util.Iterator;

public class Set<T> implements Iterable {
    private ArrayList<T> set;

    public Set() {
        set = new ArrayList<>();
    }

    public Set(T[] arr) {
        set = new ArrayList<>();
        for (T t : arr) {
            this.insert(t);
        }
    }

    public void insert(T item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }


    public void delete(T item) {
        // ArrayList.remove() removes the item only if it's present, so no need to check in advance
        set.remove(item);
    }

    public boolean isMember(T item) {
        return set.contains(item);
    }

    private ArrayList<T> getSet() {
        return set;
    }

    public void union(Set<T> otherSet) {
        ArrayList<T> arrayList = otherSet.getSet();
        for (T t : arrayList) {
            insert(t);
        }
    }

    public void intersect(Set<T> otherSet) {

        for (int i = 0; i < set.size(); i++) {
            if (!otherSet.isMember(set.get(i))) {
                this.delete(set.get(i));
                i--;
            }
        }
    }

    public boolean isSubset(Set<T> otherSet) {
        ArrayList<T> arrayList = otherSet.getSet();
        for (T t : arrayList) {
            if (!set.contains(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            res.append(set.get(i));
            if (i < set.size() - 1) {
                res.append(", ");
            }
        }
        return res.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

}
