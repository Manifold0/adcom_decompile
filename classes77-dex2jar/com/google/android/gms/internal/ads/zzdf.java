// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdf extends zzbh<Integer, Long>
{
    public Long zzfp;
    public Long zzfr;
    public Long zzfv;
    public Long zzfw;
    public Long zzsp;
    public Long zzsq;
    public Long zzsr;
    public Long zzss;
    public Long zzst;
    public Long zzsu;
    public Long zzsv;
    
    public zzdf() {
    }
    
    public zzdf(final String s) {
        this.zzj(s);
    }
    
    @Override
    protected final void zzj(final String s) {
        final HashMap<K, Long> zzk = zzbh.zzk(s);
        if (zzk != null) {
            this.zzsp = zzk.get(0);
            this.zzsq = zzk.get(1);
            this.zzsr = zzk.get(2);
            this.zzfr = zzk.get(3);
            this.zzfp = zzk.get(4);
            this.zzss = zzk.get(5);
            this.zzst = zzk.get(6);
            this.zzsu = zzk.get(7);
            this.zzfw = zzk.get(8);
            this.zzfv = zzk.get(9);
            this.zzsv = zzk.get(10);
        }
    }
    
    @Override
    protected final HashMap<Integer, Long> zzu() {
        final HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();
        hashMap.put(0, this.zzsp);
        hashMap.put(1, this.zzsq);
        hashMap.put(2, this.zzsr);
        hashMap.put(3, this.zzfr);
        hashMap.put(4, this.zzfp);
        hashMap.put(5, this.zzss);
        hashMap.put(6, this.zzst);
        hashMap.put(7, this.zzsu);
        hashMap.put(8, this.zzfw);
        hashMap.put(9, this.zzfv);
        hashMap.put(10, this.zzsv);
        return hashMap;
    }
}
