// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzbbb
{
    private static volatile boolean zzdqr;
    private static final Class<?> zzdqs;
    static final zzbbb zzdqt;
    private final Map<zzbbc, zzbbo.zzd<?, ?>> zzdqu;
    
    static {
        zzbbb.zzdqr = false;
        zzdqs = zzacq();
        zzdqt = new zzbbb(true);
    }
    
    zzbbb() {
        this.zzdqu = new HashMap<zzbbc, zzbbo.zzd<?, ?>>();
    }
    
    private zzbbb(final boolean b) {
        this.zzdqu = Collections.emptyMap();
    }
    
    private static Class<?> zzacq() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static zzbbb zzacr() {
        return zzbba.zzacp();
    }
    
    public final <ContainingType extends zzbcu> zzbbo.zzd<ContainingType, ?> zza(final ContainingType containingType, final int n) {
        return (zzbbo.zzd<ContainingType, ?>)(zzbbo.zzd)this.zzdqu.get(new zzbbc(containingType, n));
    }
}
