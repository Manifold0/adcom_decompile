// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdh extends zzbh<Integer, Long>
{
    public Long zzgj;
    public Long zzgk;
    public Long zzsx;
    
    public zzdh() {
    }
    
    public zzdh(final String s) {
        this.zzj(s);
    }
    
    @Override
    protected final void zzj(final String s) {
        final HashMap<K, Long> zzk = zzbh.zzk(s);
        if (zzk != null) {
            this.zzsx = zzk.get(0);
            this.zzgj = zzk.get(1);
            this.zzgk = zzk.get(2);
        }
    }
    
    @Override
    protected final HashMap<Integer, Long> zzu() {
        final HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();
        hashMap.put(0, this.zzsx);
        hashMap.put(1, this.zzgj);
        hashMap.put(2, this.zzgk);
        return hashMap;
    }
}
