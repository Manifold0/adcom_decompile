// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import java.util.Arrays;

public class zzc
{
    private static final char[] zzhd;
    private final byte[] zzy;
    
    static {
        zzhd = "0123456789abcdef".toCharArray();
    }
    
    protected zzc(final byte[] zzy) {
        this.zzy = zzy;
    }
    
    public static String zzf(final byte[] array) {
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (int length = array.length, i = 0; i < length; ++i) {
            final byte b = array[i];
            sb.append(zzc.zzhd[b >> 4 & 0xF]).append(zzc.zzhd[b & 0xF]);
        }
        return sb.toString();
    }
    
    public static byte[] zzm(final String s) {
        final int n = s.length() / 2;
        final byte[] array = new byte[n];
        for (int i = 0; i < n; ++i) {
            array[i] = (byte)((Character.digit(s.charAt(i * 2), 16) << 4) + Character.digit(s.charAt(i * 2 + 1), 16));
        }
        return array;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o.getClass().isAssignableFrom(this.getClass()) && Arrays.equals(this.zzy, ((zzc)o).zzy));
    }
    
    public final byte[] getBytes() {
        return this.zzy;
    }
    
    public final String getHex() {
        return zzf(this.zzy);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.zzy);
    }
    
    @Override
    public String toString() {
        return zzf(this.zzy);
    }
}
