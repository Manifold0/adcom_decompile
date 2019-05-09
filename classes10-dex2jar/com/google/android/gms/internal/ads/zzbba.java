// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbba
{
    private static final Class<?> zzdqq;
    
    static {
        zzdqq = zzaco();
    }
    
    private static Class<?> zzaco() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static zzbbb zzacp() {
        if (zzbba.zzdqq != null) {
            try {
                return (zzbbb)zzbba.zzdqq.getDeclaredMethod("getEmptyRegistry", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
            }
            catch (Exception ex) {}
        }
        return zzbbb.zzdqt;
    }
}
