package com.open9527.compiler.router;

import java.util.Collection;
import java.util.Map;

/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public final class RouterUtils {
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
