// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzays
{
    private final zzazh zzdnk;
    private final zzazh zzdnl;
    
    public zzays(final byte[] array, final byte[] array2) {
        this.zzdnk = zzazh.zzm(array);
        this.zzdnl = zzazh.zzm(array2);
    }
    
    public final byte[] zzaap() {
        if (this.zzdnk == null) {
            return null;
        }
        return this.zzdnk.getBytes();
    }
    
    public final byte[] zzaaq() {
        if (this.zzdnl == null) {
            return null;
        }
        return this.zzdnl.getBytes();
    }
}
