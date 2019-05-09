// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcx extends zzbh<Integer, Object>
{
    public Long zzro;
    public Boolean zzrp;
    public Boolean zzrq;
    
    public zzcx() {
    }
    
    public zzcx(final String s) {
        this.zzj(s);
    }
    
    @Override
    protected final void zzj(final String s) {
        final HashMap<K, Long> zzk = zzbh.zzk(s);
        if (zzk != null) {
            this.zzro = zzk.get(0);
            this.zzrp = (Boolean)(Object)zzk.get(1);
            this.zzrq = (Boolean)(Object)zzk.get(2);
        }
    }
    
    @Override
    protected final HashMap<Integer, Object> zzu() {
        final HashMap<Integer, Long> hashMap = (HashMap<Integer, Long>)new HashMap<Integer, Boolean>();
        hashMap.put(Integer.valueOf(0), (Boolean)(Object)this.zzro);
        hashMap.put(Integer.valueOf(1), this.zzrp);
        hashMap.put(Integer.valueOf(2), this.zzrq);
        return (HashMap<Integer, Object>)hashMap;
    }
}
