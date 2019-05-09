// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

abstract class zzben
{
    abstract int zzb(final int p0, final byte[] p1, final int p2, final int p3);
    
    abstract int zzb(final CharSequence p0, final byte[] p1, final int p2, final int p3);
    
    final boolean zzf(final byte[] array, final int n, final int n2) {
        boolean b = false;
        if (this.zzb(0, array, n, n2) == 0) {
            b = true;
        }
        return b;
    }
}
