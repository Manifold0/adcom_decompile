// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzazk
{
    private static long zza(final byte[] array, final int n, final int n2) {
        return zzd(array, n) >> n2 & 0x3FFFFFFL;
    }
    
    private static void zza(final byte[] array, long n, final int n2) {
        for (int i = 0; i < 4; ++i, n >>= 8) {
            array[n2 + i] = (byte)(0xFFL & n);
        }
    }
    
    private static long zzd(final byte[] array, final int n) {
        return (long)((array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | (array[n + 3] & 0xFF) << 24) & 0xFFFFFFFFL;
    }
    
    static byte[] zze(byte[] array, final byte[] array2) {
        if (array.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long n = 0L;
        long n2 = 0L;
        long n3 = 0L;
        long n4 = 0L;
        long n5 = 0L;
        final long n6 = 0x3FFFFFFL & zza(array, 0, 0);
        final long n7 = 0x3FFFF03L & zza(array, 3, 2);
        final long n8 = 0x3FFC0FFL & zza(array, 6, 4);
        final long n9 = 0x3F03FFFL & zza(array, 9, 6);
        final long n10 = 0xFFFFFL & zza(array, 12, 8);
        final long n11 = n8 * 5L;
        final long n12 = n9 * 5L;
        final long n13 = n10 * 5L;
        final byte[] array3 = new byte[17];
        for (int i = 0; i < array2.length; i += 16) {
            final int min = Math.min(16, array2.length - i);
            System.arraycopy(array2, i, array3, 0, min);
            array3[min] = 1;
            if (min != 16) {
                Arrays.fill(array3, min + 1, 17, (byte)0);
            }
            final long n14 = n + zza(array3, 0, 0);
            final long n15 = n2 + zza(array3, 3, 2);
            final long n16 = n3 + zza(array3, 6, 4);
            final long n17 = n4 + zza(array3, 9, 6);
            final long n18 = n5 + (zza(array3, 12, 8) | (long)(array3[16] << 24));
            final long n19 = n14 * n6 + n15 * n13 + n16 * n12 + n17 * n11 + n18 * (n7 * 5L);
            final long n20 = (n19 >> 26) + (n14 * n7 + n15 * n6 + n16 * n13 + n17 * n12 + n18 * n11);
            final long n21 = n14 * n8 + n15 * n7 + n16 * n6 + n17 * n13 + n18 * n12 + (n20 >> 26);
            n3 = (0x3FFFFFFL & n21);
            final long n22 = n14 * n9 + n15 * n8 + n16 * n7 + n17 * n6 + n18 * n13 + (n21 >> 26);
            n4 = (n22 & 0x3FFFFFFL);
            final long n23 = n18 * n6 + (n17 * n7 + (n16 * n8 + (n15 * n9 + n14 * n10))) + (n22 >> 26);
            n5 = (n23 & 0x3FFFFFFL);
            final long n24 = (n19 & 0x3FFFFFFL) + (n23 >> 26) * 5L;
            n = (0x3FFFFFFL & n24);
            n2 = (n20 & 0x3FFFFFFL) + (n24 >> 26);
        }
        final long n25 = (n2 >> 26) + n3;
        final long n26 = n25 & 0x3FFFFFFL;
        final long n27 = n4 + (n25 >> 26);
        final long n28 = n27 & 0x3FFFFFFL;
        final long n29 = n5 + (n27 >> 26);
        final long n30 = n29 & 0x3FFFFFFL;
        final long n31 = (n29 >> 26) * 5L + n;
        final long n32 = n31 & 0x3FFFFFFL;
        final long n33 = (n2 & 0x3FFFFFFL) + (n31 >> 26);
        final long n34 = 5L + n32;
        final long n35 = (n34 >> 26) + n33;
        final long n36 = (n35 >> 26) + n26;
        final long n37 = (n36 >> 26) + n28;
        final long n38 = (n37 >> 26) + n30 - 67108864L;
        final long n39 = n38 >> 63;
        final long n40 = ~n39;
        final long n41 = (n33 & n39) | (n35 & 0x3FFFFFFL & n40);
        final long n42 = (n26 & n39) | (n36 & 0x3FFFFFFL & n40);
        final long n43 = (n28 & n39) | (n37 & 0x3FFFFFFL & n40);
        final long n44 = zzd(array, 16) + (((n32 & n39) | (n34 & 0x3FFFFFFL & n40) | n41 << 26) & 0xFFFFFFFFL);
        final long n45 = (n44 >> 32) + (((n41 >> 6 | n42 << 20) & 0xFFFFFFFFL) + zzd(array, 20));
        final long n46 = ((n42 >> 12 | n43 << 14) & 0xFFFFFFFFL) + zzd(array, 24) + (n45 >> 32);
        final long zzd = zzd(array, 28);
        array = new byte[16];
        zza(array, 0xFFFFFFFFL & n44, 0);
        zza(array, 0xFFFFFFFFL & n45, 4);
        zza(array, 0xFFFFFFFFL & n46, 8);
        zza(array, (n46 >> 32) + (((((n30 & n39) | (n38 & n40)) << 8 | n43 >> 18) & 0xFFFFFFFFL) + zzd) & 0xFFFFFFFFL, 12);
        return array;
    }
}
