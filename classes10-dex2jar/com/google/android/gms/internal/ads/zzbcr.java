// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbcr
{
    private static final zzbcp zzdwd;
    private static final zzbcp zzdwe;
    
    static {
        zzdwd = zzaeg();
        zzdwe = new zzbcq();
    }
    
    static zzbcp zzaee() {
        return zzbcr.zzdwd;
    }
    
    static zzbcp zzaef() {
        return zzbcr.zzdwe;
    }
    
    private static zzbcp zzaeg() {
        try {
            return (zzbcp)Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
