// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;
import java.lang.reflect.Array;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public final class ArrayUtils
{
    private ArrayUtils() {
    }
    
    @KeepForSdk
    public static <T> T[] appendToArray(final T[] array, final T t) {
        if (array == null && t == null) {
            throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
        }
        Object[] copy;
        if (array == null) {
            copy = (Object[])Array.newInstance(t.getClass(), 1);
        }
        else {
            copy = Arrays.copyOf(array, array.length + 1);
        }
        copy[copy.length - 1] = t;
        return (T[])copy;
    }
    
    @KeepForSdk
    public static <T> T[] concat(final T[]... array) {
        if (array.length == 0) {
            return (T[])Array.newInstance(array.getClass(), 0);
        }
        int i = 0;
        int n = 0;
        while (i < array.length) {
            n += array[i].length;
            ++i;
        }
        final T[] copy = Arrays.copyOf(array[0], n);
        int length = array[0].length;
        for (int j = 1; j < array.length; ++j) {
            final T[] array2 = array[j];
            System.arraycopy(array2, 0, copy, length, array2.length);
            length += array2.length;
        }
        return copy;
    }
    
    @KeepForSdk
    public static byte[] concatByteArrays(final byte[]... array) {
        if (array.length == 0) {
            return new byte[0];
        }
        int i = 0;
        int n = 0;
        while (i < array.length) {
            n += array[i].length;
            ++i;
        }
        final byte[] copy = Arrays.copyOf(array[0], n);
        int length = array[0].length;
        for (int j = 1; j < array.length; ++j) {
            final byte[] array2 = array[j];
            System.arraycopy(array2, 0, copy, length, array2.length);
            length += array2.length;
        }
        return copy;
    }
    
    @KeepForSdk
    public static boolean contains(final int[] array, final int n) {
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                if (array[i] == n) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @KeepForSdk
    public static <T> boolean contains(final T[] array, final T t) {
        boolean b = false;
        int length;
        if (array != null) {
            length = array.length;
        }
        else {
            length = 0;
        }
        while (true) {
            for (int i = 0; i < length; ++i) {
                if (Objects.equal(array[i], t)) {
                    if (i >= 0) {
                        b = true;
                    }
                    return b;
                }
            }
            int i = -1;
            continue;
        }
    }
    
    @KeepForSdk
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<T>();
    }
    
    @KeepForSdk
    public static <T> T[] removeAll(final T[] array, final T... array2) {
        int n = 0;
        Object[] array3;
        if (array == null) {
            array3 = null;
        }
        else {
            if (array2 == null || array2.length == 0) {
                return Arrays.copyOf(array, array.length);
            }
            final Object[] array4 = (Object[])Array.newInstance(array2.getClass().getComponentType(), array.length);
            int n4;
            if (array2.length == 1) {
                final int length = array.length;
                int n2 = 0;
                int n3 = 0;
                while (true) {
                    n4 = n3;
                    if (n2 >= length) {
                        break;
                    }
                    final T t = array[n2];
                    if (!Objects.equal(array2[0], t)) {
                        final int n5 = n3 + 1;
                        array4[n3] = t;
                        n3 = n5;
                    }
                    ++n2;
                }
            }
            else {
                final int length2 = array.length;
                int n6 = 0;
                while (true) {
                    n4 = n6;
                    if (n >= length2) {
                        break;
                    }
                    final T t2 = array[n];
                    if (!contains(array2, t2)) {
                        final int n7 = n6 + 1;
                        array4[n6] = t2;
                        n6 = n7;
                    }
                    ++n;
                }
            }
            if (array4 == null) {
                return null;
            }
            array3 = array4;
            if (n4 != ((T[])array4).length) {
                return Arrays.copyOf(array4, n4);
            }
        }
        return (T[])array3;
    }
    
    @KeepForSdk
    public static <T> ArrayList<T> toArrayList(final T[] array) {
        final int length = array.length;
        final ArrayList list = new ArrayList<T>(length);
        for (int i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        return (ArrayList<T>)list;
    }
    
    @KeepForSdk
    public static int[] toPrimitiveArray(final Collection<Integer> collection) {
        if (collection == null || collection.size() == 0) {
            return new int[0];
        }
        final int[] array = new int[collection.size()];
        final Iterator<Integer> iterator = collection.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            array[n] = iterator.next();
            ++n;
        }
        return array;
    }
    
    @KeepForSdk
    public static Integer[] toWrapperArray(final int[] array) {
        Integer[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            final int length = array.length;
            final Integer[] array3 = new Integer[length];
            int n = 0;
            while (true) {
                array2 = array3;
                if (n >= length) {
                    break;
                }
                array3[n] = array[n];
                ++n;
            }
        }
        return array2;
    }
    
    @KeepForSdk
    public static void writeArray(final StringBuilder sb, final double[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Double.toString(array[i]));
        }
    }
    
    @KeepForSdk
    public static void writeArray(final StringBuilder sb, final float[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Float.toString(array[i]));
        }
    }
    
    @KeepForSdk
    public static void writeArray(final StringBuilder sb, final int[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Integer.toString(array[i]));
        }
    }
    
    @KeepForSdk
    public static void writeArray(final StringBuilder sb, final long[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(array[i]));
        }
    }
    
    @KeepForSdk
    public static <T> void writeArray(final StringBuilder sb, final T[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(array[i].toString());
        }
    }
    
    @KeepForSdk
    public static void writeArray(final StringBuilder sb, final boolean[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Boolean.toString(array[i]));
        }
    }
    
    @KeepForSdk
    public static void writeStringArray(final StringBuilder sb, final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("\"").append(array[i]).append("\"");
        }
    }
}
