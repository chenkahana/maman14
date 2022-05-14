package Question1;

import java.util.Iterator;

public class MinValue<T extends Comparable<T>> {

    public T getMinValue(Set<T> set) {
        Iterator<T> it = set.iterator();
        T min = it.next();
        while (it.hasNext()) {
            T curr = it.next();
            if (min.compareTo(curr) > 0) {
                min = curr;
            }
        }
        return min;
    }
}

