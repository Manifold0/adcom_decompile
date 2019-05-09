// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ii
{
    private static final char[] a;
    private static final char[] b;
    private final String c;
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        b = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    public static char[] a(final byte[] array) {
        int n = 0;
        final char[] a = ii.a;
        final int length = array.length;
        final char[] array2 = new char[length << 1];
        for (int i = 0; i < length; ++i) {
            final int n2 = n + 1;
            array2[n] = a[(array[i] & 0xF0) >>> 4];
            n = n2 + 1;
            array2[n2] = a[array[i] & 0xF];
        }
        return array2;
    }
    
    @Override
    public final String toString() {
        return super.toString() + "[charsetName=" + this.c + "]";
    }
}
