// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzbj extends zzbh<Integer, Object>
{
    public String zzcx;
    public String zzcz;
    public String zzda;
    public String zzdb;
    public long zzhx;
    
    public zzbj() {
        this.zzcx = "E";
        this.zzhx = -1L;
        this.zzcz = "E";
        this.zzda = "E";
        this.zzdb = "E";
    }
    
    public zzbj(final String s) {
        this();
        this.zzj(s);
    }
    
    @Override
    protected final void zzj(String s) {
        final HashMap<K, String> zzk = zzbh.zzk(s);
        if (zzk != null) {
            if (zzk.get(0) == null) {
                s = "E";
            }
            else {
                s = zzk.get(0);
            }
            this.zzcx = s;
            long longValue;
            if (zzk.get(1) == null) {
                longValue = -1L;
            }
            else {
                longValue = (long)zzk.get(1);
            }
            this.zzhx = longValue;
            if (zzk.get(2) == null) {
                s = "E";
            }
            else {
                s = zzk.get(2);
            }
            this.zzcz = s;
            if (zzk.get(3) == null) {
                s = "E";
            }
            else {
                s = zzk.get(3);
            }
            this.zzda = s;
            if (zzk.get(4) == null) {
                s = "E";
            }
            else {
                s = zzk.get(4);
            }
            this.zzdb = s;
        }
    }
    
    @Override
    protected final HashMap<Integer, Object> zzu() {
        final HashMap<Integer, String> hashMap = (HashMap<Integer, String>)new HashMap<Integer, Long>();
        hashMap.put(Integer.valueOf(0), (Long)this.zzcx);
        hashMap.put(Integer.valueOf(4), (Long)this.zzdb);
        hashMap.put(Integer.valueOf(3), (Long)this.zzda);
        hashMap.put(Integer.valueOf(2), (Long)this.zzcz);
        hashMap.put(Integer.valueOf(1), Long.valueOf(this.zzhx));
        return (HashMap<Integer, Object>)hashMap;
    }
}
