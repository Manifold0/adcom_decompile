// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONObject;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

@zzadh
public abstract class zzna<T>
{
    private final T mDefaultValue;
    private final String mKey;
    private final int zzatr;
    
    private zzna(final int zzatr, final String mKey, final T mDefaultValue) {
        this.zzatr = zzatr;
        this.mKey = mKey;
        this.mDefaultValue = mDefaultValue;
        zzkb.zzij().zza(this);
    }
    
    public static zzna<String> zza(final int n, final String s) {
        final zzna<String> zza = zza(n, s, (String)null);
        zzkb.zzij().zzb(zza);
        return zza;
    }
    
    public static zzna<Float> zza(final int n, final String s, final float n2) {
        return new zzne(n, s, n2);
    }
    
    public static zzna<Integer> zza(final int n, final String s, final int n2) {
        return new zznc(n, s, n2);
    }
    
    public static zzna<Long> zza(final int n, final String s, final long n2) {
        return new zznd(n, s, n2);
    }
    
    public static zzna<Boolean> zza(final int n, final String s, final Boolean b) {
        return new zznb(n, s, b);
    }
    
    public static zzna<String> zza(final int n, final String s, final String s2) {
        return new zznf(n, s, s2);
    }
    
    public static zzna<String> zzb(final int n, final String s) {
        final zzna<String> zza = zza(n, s, (String)null);
        zzkb.zzij().zzc(zza);
        return zza;
    }
    
    public final String getKey() {
        return this.mKey;
    }
    
    public final int getSource() {
        return this.zzatr;
    }
    
    protected abstract T zza(final SharedPreferences p0);
    
    public abstract void zza(final SharedPreferences$Editor p0, final T p1);
    
    protected abstract T zzb(final JSONObject p0);
    
    public final T zzja() {
        return this.mDefaultValue;
    }
}
