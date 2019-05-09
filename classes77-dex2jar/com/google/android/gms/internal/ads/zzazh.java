// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzazh
{
    private final byte[] data;
    
    private zzazh(final byte[] array, final int n, final int n2) {
        System.arraycopy(array, 0, this.data = new byte[n2], 0, n2);
    }
    
    public static zzazh zzm(final byte[] array) {
        if (array == null) {
            return null;
        }
        return new zzazh(array, 0, array.length);
    }
    
    public final byte[] getBytes() {
        final byte[] array = new byte[this.data.length];
        System.arraycopy(this.data, 0, array, 0, this.data.length);
        return array;
    }
}
