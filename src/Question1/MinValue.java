package Question1;

import java.util.Iterator;

public class MinValue<T> {

    public T getMinValue(Set<T> set) {
        Iterator<T> it = set.iterator();
        T min;
        if (it.hasNext()) {
            min = it.next();
        } else {
            return null;
        }
        try {
            while (it.hasNext()) {
                T curr = it.next();
                if (((Comparable<T>) min).compareTo(curr) > 0) {
                    min = curr;
                }

            }
        } catch (Exception exception) {
            return min;
        }
        return min;
    }

}

