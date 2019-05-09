// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcy extends zzbh<Integer, Long>
{
    public Long zzrr;
    public Long zzrs;
    
    public zzcy() {
    }
    
    public zzcy(final String s) {
        this.zzj(s);
    }
    
    @Override
    protected final void zzj(final String s) {
        final HashMap<K, Long> zzk = zzbh.zzk(s);
        if (zzk != null) {
            this.zzrr = zzk.get(0);
            this.zzrs = zzk.get(1);
        }
    }
    
    @Override
    protected final HashMap<Integer, Long> zzu() {
        final HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();
        hashMap.put(0, this.zzrr);
        hashMap.put(1, this.zzrs);
        return hashMap;
    }
}
