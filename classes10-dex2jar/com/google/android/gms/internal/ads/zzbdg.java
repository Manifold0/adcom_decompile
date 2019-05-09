// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzbdg
{
    private static final zzbdg zzdxa;
    private final zzbdn zzdxb;
    private final ConcurrentMap<Class<?>, zzbdm<?>> zzdxc;
    
    static {
        zzdxa = new zzbdg();
    }
    
    private zzbdg() {
        this.zzdxc = new ConcurrentHashMap<Class<?>, zzbdm<?>>();
        zzbdn zzbdn = null;
        zzbdn zzeq;
        for (int i = 0; i <= 0; ++i, zzbdn = zzeq) {
            zzeq = zzeq((new String[] { "com.google.protobuf.AndroidProto3SchemaFactory" })[0]);
            if ((zzbdn = zzeq) != null) {
                break;
            }
        }
        zzbdn zzdxb;
        if ((zzdxb = zzbdn) == null) {
            zzdxb = new zzbcj();
        }
        this.zzdxb = zzdxb;
    }
    
    public static zzbdg zzaeo() {
        return zzbdg.zzdxa;
    }
    
    private static zzbdn zzeq(final String s) {
        try {
            return (zzbdn)Class.forName(s).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public final <T> zzbdm<T> zzab(final T t) {
        return this.zze(t.getClass());
    }
    
    public final <T> zzbdm<T> zze(final Class<T> clazz) {
        zzbbq.zza(clazz, "messageType");
        zzbdm<?> zzbdm;
        if ((zzbdm = this.zzdxc.get(clazz)) == null) {
            final zzbdm<T> zzd = this.zzdxb.zzd(clazz);
            zzbbq.zza(clazz, "messageType");
            zzbbq.zza(zzd, "schema");
            zzbdm = this.zzdxc.putIfAbsent(clazz, zzd);
            if (zzbdm == null) {
                return zzd;
            }
        }
        return (zzbdm<T>)zzbdm;
    }
}
