// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@ShowFirstParty
public class Hex
{
    private static final char[] zzgy;
    private static final char[] zzgz;
    
    static {
        zzgy = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        zzgz = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
    
    @KeepForSdk
    public static String bytesToStringLowercase(final byte[] array) {
        int i = 0;
        final char[] array2 = new char[array.length << 1];
        int n = 0;
        while (i < array.length) {
            final int n2 = array[i] & 0xFF;
            final int n3 = n + 1;
            array2[n] = Hex.zzgz[n2 >>> 4];
            n = n3 + 1;
            array2[n3] = Hex.zzgz[n2 & 0xF];
            ++i;
        }
        return new String(array2);
    }
    
    @KeepForSdk
    public static String bytesToStringUppercase(final byte[] array) {
        return bytesToStringUppercase(array, false);
    }
    
    @KeepForSdk
    public static String bytesToStringUppercase(final byte[] array, final boolean b) {
        final int length = array.length;
        final StringBuilder sb = new StringBuilder(length << 1);
        for (int n = 0; n < length && (!b || n != length - 1 || (array[n] & 0xFF) != 0x0); ++n) {
            sb.append(Hex.zzgy[(array[n] & 0xF0) >>> 4]);
            sb.append(Hex.zzgy[array[n] & 0xF]);
        }
        return sb.toString();
    }
    
    @KeepForSdk
    public static byte[] stringToBytes(final String s) throws IllegalArgumentException {
        final int length = s.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Hex string has odd number of characters");
        }
        final byte[] array = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            array[i / 2] = (byte)Integer.parseInt(s.substring(i, i + 2), 16);
        }
        return array;
    }
}
