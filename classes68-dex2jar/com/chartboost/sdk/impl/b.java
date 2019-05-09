// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

public class b
{
    private static void a(final int[] array) {
        for (int i = 0; i < array.length / 2; ++i) {
            final int n = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = n;
        }
    }
    
    public static int[] a(final char[] array, final int[] array2, final boolean b) {
        int n = (array[0] << 16) + array[1];
        int n2 = array[3] + (array[2] << 16);
        if (!b) {
            a(array2);
        }
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        for (int i = 0; i < 16; ++i, n8 = (n2 ^ n7 + (n4 + n5 ^ n6)), n2 = n3, n = n8) {
            n3 = (n ^ array2[i]);
            final a b2 = a.b;
            n4 = b2.a[0][n3 >>> 24];
            n5 = b2.a[1][n3 >>> 16 & 0xFF];
            n6 = b2.a[2][n3 >>> 8 & 0xFF];
            n7 = b2.a[3][n3 & 0xFF];
        }
        final int n9 = array2[16] ^ n;
        final int n10 = n2 ^ array2[17];
        array[0] = (char)(n10 >>> 16);
        array[1] = (char)n10;
        array[2] = (char)(n9 >>> 16);
        array[3] = (char)n9;
        if (!b) {
            a(array2);
        }
        return new int[] { n10, n9 };
    }
}
