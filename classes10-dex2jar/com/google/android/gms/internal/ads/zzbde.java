// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbde
{
    private static final zzbdc zzdwy;
    private static final zzbdc zzdwz;
    
    static {
        zzdwy = zzaen();
        zzdwz = new zzbdd();
    }
    
    static zzbdc zzael() {
        return zzbde.zzdwy;
    }
    
    static zzbdc zzaem() {
        return zzbde.zzdwz;
    }
    
    private static zzbdc zzaen() {
        try {
            return (zzbdc)Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
