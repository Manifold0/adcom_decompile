// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

public class h
{
    private static final char[] a;
    private static final char[] b;
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        b = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    public static char[] a(final byte[] array) {
        return a(array, true);
    }
    
    public static char[] a(final byte[] array, final boolean b) {
        char[] array2;
        if (b) {
            array2 = h.a;
        }
        else {
            array2 = h.b;
        }
        return a(array, array2);
    }
    
    protected static char[] a(final byte[] array, final char[] array2) {
        int n = 0;
        final int length = array.length;
        final char[] array3 = new char[length << 1];
        for (int i = 0; i < length; ++i) {
            final int n2 = n + 1;
            array3[n] = array2[(array[i] & 0xF0) >>> 4];
            n = n2 + 1;
            array3[n2] = array2[array[i] & 0xF];
        }
        return array3;
    }
    
    public static String b(final byte[] array) {
        return new String(a(array));
    }
}
