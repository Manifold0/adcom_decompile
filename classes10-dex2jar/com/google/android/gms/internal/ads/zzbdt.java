// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzbdt
{
    private static final Iterator<Object> zzdyr;
    private static final Iterable<Object> zzdys;
    
    static {
        zzdyr = new zzbdu();
        zzdys = new zzbdv();
    }
    
    static <T> Iterable<T> zzafy() {
        return (Iterable<T>)zzbdt.zzdys;
    }
}
