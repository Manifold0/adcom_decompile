// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;

abstract class zzbce
{
    private static final zzbce zzdvq;
    private static final zzbce zzdvr;
    
    static {
        zzdvq = new zzbcg(null);
        zzdvr = new zzbch(null);
    }
    
    private zzbce() {
    }
    
    static zzbce zzady() {
        return zzbce.zzdvq;
    }
    
    static zzbce zzadz() {
        return zzbce.zzdvr;
    }
    
    abstract <L> List<L> zza(final Object p0, final long p1);
    
    abstract <L> void zza(final Object p0, final Object p1, final long p2);
    
    abstract void zzb(final Object p0, final long p1);
}
