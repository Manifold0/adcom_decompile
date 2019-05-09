// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcm extends zzbh<Integer, Long>
{
    public long zzri;
    public long zzrj;
    
    public zzcm() {
        this.zzri = -1L;
        this.zzrj = -1L;
    }
    
    public zzcm(final String s) {
        this();
        this.zzj(s);
    }
    
    @Override
    protected final void zzj(final String s) {
        final HashMap<K, Long> zzk = zzbh.zzk(s);
        if (zzk != null) {
            this.zzri = zzk.get(0);
            this.zzrj = zzk.get(1);
        }
    }
    
    @Override
    protected final HashMap<Integer, Long> zzu() {
        final HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();
        hashMap.put(0, this.zzri);
        hashMap.put(1, this.zzrj);
        return hashMap;
    }
}
