// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable
{
    private static final Object zzdc;
    private static ClassLoader zzdd;
    private static Integer zzde;
    private boolean zzdf;
    
    static {
        zzdc = new Object();
        DowngradeableSafeParcel.zzdd = null;
        DowngradeableSafeParcel.zzde = null;
    }
    
    public DowngradeableSafeParcel() {
        this.zzdf = false;
    }
    
    @KeepForSdk
    protected static boolean canUnparcelSafely(final String s) {
        zzp();
        return true;
    }
    
    @KeepForSdk
    protected static Integer getUnparcelClientVersion() {
        synchronized (DowngradeableSafeParcel.zzdc) {
            // monitorexit(DowngradeableSafeParcel.zzdc)
            return null;
        }
    }
    
    private static ClassLoader zzp() {
        synchronized (DowngradeableSafeParcel.zzdc) {
            // monitorexit(DowngradeableSafeParcel.zzdc)
            return null;
        }
    }
    
    @KeepForSdk
    protected abstract boolean prepareForClientVersion(final int p0);
    
    @KeepForSdk
    public void setShouldDowngrade(final boolean zzdf) {
        this.zzdf = zzdf;
    }
    
    @KeepForSdk
    protected boolean shouldDowngrade() {
        return this.zzdf;
    }
}
