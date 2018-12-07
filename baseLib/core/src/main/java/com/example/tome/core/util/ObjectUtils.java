package com.example.tome.core.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author by TOME .
 * @data on      2018/6/25 14:07
 * @describe ${对象相关}
 */

public class ObjectUtils {

    /**
     * 判断对象是否为空
     *
     * @param obj The object.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof CharSequence && obj.toString().length() == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SimpleArrayMap && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        if (obj instanceof LongSparseArray && ((LongSparseArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (obj instanceof android.util.LongSparseArray
                    && ((android.util.LongSparseArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(final CharSequence obj) {
        return obj == null || obj.toString().length() == 0;
    }

    public static boolean isEmpty(final Collection obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(final Map obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(final SimpleArrayMap obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(final SparseArray obj) {
        return obj == null || obj.size() == 0;
    }

    public static boolean isEmpty(final SparseBooleanArray obj) {
        return obj == null || obj.size() == 0;
    }

    public static boolean isEmpty(final SparseIntArray obj) {
        return obj == null || obj.size() == 0;
    }

    public static boolean isEmpty(final LongSparseArray obj) {
        return obj == null || obj.size() == 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean isEmpty(final SparseLongArray obj) {
        return obj == null || obj.size() == 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static boolean isEmpty(final android.util.LongSparseArray obj) {
        return obj == null || obj.size() == 0;
    }


    /**
     * 判断对象是否非空
     *
     * @param obj The object.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNotEmpty(final Object obj) {
        return !isEmpty(obj);
    }


    public static boolean isNotEmpty(final CharSequence obj) {
        return !isEmpty(obj);
    }
    public static boolean isNotEmpty(final Collection obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(final Map obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(final SimpleArrayMap obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(final SparseArray obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(final SparseBooleanArray obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(final SparseIntArray obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(final LongSparseArray obj) {
        return !isEmpty(obj);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean isNotEmpty(final SparseLongArray obj) {
        return !isEmpty(obj);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static boolean isNotEmpty(final android.util.LongSparseArray obj) {
        return !isEmpty(obj);
    }


    /**
     * 判断对象是否相等
     *
     * @param o1 The first object.
     * @param o2 The second object.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean equals(final Object o1, final Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }

}
