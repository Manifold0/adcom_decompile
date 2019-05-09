// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbbf
{
    private static final zzbbd<?> zzdqv;
    private static final zzbbd<?> zzdqw;
    
    static {
        zzdqv = new zzbbe();
        zzdqw = zzacs();
    }
    
    private static zzbbd<?> zzacs() {
        try {
            return (zzbbd<?>)Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static zzbbd<?> zzact() {
        return zzbbf.zzdqv;
    }
    
    static zzbbd<?> zzacu() {
        if (zzbbf.zzdqw == null) {
            throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
        }
        return zzbbf.zzdqw;
    }
}
