// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;

public final class ik
{
    public static void a(final StringBuffer sb, final Object o, final Map map) {
        if (o == null) {
            sb.append("null");
            return;
        }
        if (!o.getClass().isArray()) {
            try {
                sb.append(o.toString());
                return;
            }
            catch (Throwable t) {
                System.err.println("SLF4J: Failed toString() invocation on an object of type [" + o.getClass().getName() + "]");
                t.printStackTrace();
                sb.append("[FAILED toString()]");
                return;
            }
        }
        if (o instanceof boolean[]) {
            a(sb, (boolean[])o);
            return;
        }
        if (o instanceof byte[]) {
            a(sb, (byte[])o);
            return;
        }
        if (o instanceof char[]) {
            a(sb, (char[])o);
            return;
        }
        if (o instanceof short[]) {
            a(sb, (short[])o);
            return;
        }
        if (o instanceof int[]) {
            a(sb, (int[])o);
            return;
        }
        if (o instanceof long[]) {
            a(sb, (long[])o);
            return;
        }
        if (o instanceof float[]) {
            a(sb, (float[])o);
            return;
        }
        if (o instanceof double[]) {
            a(sb, (double[])o);
            return;
        }
        a(sb, (Object[])o, map);
    }
    
    private static void a(final StringBuffer sb, final byte[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final char[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final double[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final float[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final int[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final long[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final Object[] array, final Map map) {
        sb.append('[');
        if (!map.containsKey(array)) {
            map.put(array, null);
            for (int length = array.length, i = 0; i < length; ++i) {
                a(sb, array[i], map);
                if (i != length - 1) {
                    sb.append(", ");
                }
            }
            map.remove(array);
        }
        else {
            sb.append("...");
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final short[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
    
    private static void a(final StringBuffer sb, final boolean[] array) {
        sb.append('[');
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(array[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }
}
