// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.os.Bundle;

public final class zzi
{
    public static final zzi zziad;
    private static zzi zziae;
    private final int zziaf;
    private final int zziag;
    private final int zziah;
    
    static {
        zziad = new zzi(0, 30, 3600);
        zzi.zziae = new zzi(1, 30, 3600);
    }
    
    private zzi(final int zziaf, final int n, final int n2) {
        this.zziaf = zziaf;
        this.zziag = 30;
        this.zziah = 3600;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzi)) {
                return false;
            }
            final zzi zzi = (zzi)o;
            if (zzi.zziaf != this.zziaf || zzi.zziag != this.zziag || zzi.zziah != this.zziah) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        return ((this.zziaf + 1 ^ 0xF4243) * 1000003 ^ this.zziag) * 1000003 ^ this.zziah;
    }
    
    @Override
    public final String toString() {
        return new StringBuilder(74).append("policy=").append(this.zziaf).append(" initial_backoff=").append(this.zziag).append(" maximum_backoff=").append(this.zziah).toString();
    }
    
    public final int zzaul() {
        return this.zziaf;
    }
    
    public final int zzaum() {
        return this.zziag;
    }
    
    public final int zzaun() {
        return this.zziah;
    }
    
    public final Bundle zzu(final Bundle bundle) {
        bundle.putInt("retry_policy", this.zziaf);
        bundle.putInt("initial_backoff_seconds", this.zziag);
        bundle.putInt("maximum_backoff_seconds", this.zziah);
        return bundle;
    }
}
