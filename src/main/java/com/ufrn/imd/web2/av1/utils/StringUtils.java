package com.ufrn.imd.web2.av1.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }
}
