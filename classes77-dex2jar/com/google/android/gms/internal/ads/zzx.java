// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzx<T>
{
    public final T result;
    public final zzc zzbg;
    public final zzae zzbh;
    public boolean zzbi;
    
    private zzx(final zzae zzbh) {
        this.zzbi = false;
        this.result = null;
        this.zzbg = null;
        this.zzbh = zzbh;
    }
    
    private zzx(final T result, final zzc zzbg) {
        this.zzbi = false;
        this.result = result;
        this.zzbg = zzbg;
        this.zzbh = null;
    }
    
    public static <T> zzx<T> zza(final T t, final zzc zzc) {
        return new zzx<T>(t, zzc);
    }
    
    public static <T> zzx<T> zzc(final zzae zzae) {
        return new zzx<T>(zzae);
    }
}
