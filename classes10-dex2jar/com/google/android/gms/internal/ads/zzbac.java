// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbac
{
    private static final Class<?> zzdpj;
    private static final boolean zzdpk;
    
    static {
        zzdpj = zzel("libcore.io.Memory");
        zzdpk = (zzel("org.robolectric.Robolectric") != null);
    }
    
    static boolean zzabb() {
        return zzbac.zzdpj != null && !zzbac.zzdpk;
    }
    
    static Class<?> zzabc() {
        return zzbac.zzdpj;
    }
    
    private static <T> Class<T> zzel(final String s) {
        try {
            return (Class<T>)Class.forName(s);
        }
        catch (Throwable t) {
            return null;
        }
    }
}
