package com.ufrn.imd.web2.av1.utils;

import java.math.BigDecimal;
import java.util.Collection;

public class ValidatorUtils {

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            return isEmpty((String) o);
        } else if (o instanceof final Number i) {
            if (!(i instanceof Double) && !(i instanceof BigDecimal)) {
                return i.intValue() == 0;
            } else {
                return i.doubleValue() == 0.0;
            }
        } else if (o instanceof Object[]) {
            return ((Object[]) o).length == 0;
        } else if (o instanceof int[]) {
            return ((int[]) o).length == 0;
        } else if (o instanceof Collection) {
            return ((Collection<?>) o).isEmpty();
        }
        return false;
    }
}
