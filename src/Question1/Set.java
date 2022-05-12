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

    public void union(Set<T> otherSet) {
        Iterator<T> it = otherSet.iterator();
        while (it.hasNext()) {
            insert(it.next());
        }
    }

    public void intersect(Set<T> otherSet) {
        Iterator<T> it = iterator();
        ArrayList<T> retArr = new ArrayList<>();
        while (it.hasNext()) {
            T element = it.next();
            if (otherSet.isMember(element)) {
                retArr.add(element);
            }
        }
        set = retArr;
    }

    public boolean isSubset(Set<T> otherSet) {
        Iterator<T> it = otherSet.iterator();
        while (it.hasNext()) {
            if (!isMember(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            res.append(it.next());
            if (it.hasNext()) {
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
