package nutrition.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by andrius on 3/21/17.
 */

public class Transformer {

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> collection = new ArrayList<E>();
        for (E item : iter) {
            collection.add(item);
        }
        return collection;
    }

}
