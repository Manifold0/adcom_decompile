// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzaju
{
    private final Object mLock;
    private final zzajv zzacn;
    private final String zzcov;
    @GuardedBy("mLock")
    private int zzcql;
    @GuardedBy("mLock")
    private int zzcqm;
    
    private zzaju(final zzajv zzacn, final String zzcov) {
        this.mLock = new Object();
        this.zzacn = zzacn;
        this.zzcov = zzcov;
    }
    
    public zzaju(final String s) {
        this(zzbv.zzep(), s);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final zzaju zzaju = (zzaju)o;
            if (this.zzcov != null) {
                return this.zzcov.equals(zzaju.zzcov);
            }
            if (zzaju.zzcov != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        if (this.zzcov != null) {
            return this.zzcov.hashCode();
        }
        return 0;
    }
    
    public final Bundle toBundle() {
        synchronized (this.mLock) {
            final Bundle bundle = new Bundle();
            bundle.putInt("pmnli", this.zzcql);
            bundle.putInt("pmnll", this.zzcqm);
            return bundle;
        }
    }
    
    public final void zze(final int zzcql, final int zzcqm) {
        synchronized (this.mLock) {
            this.zzcql = zzcql;
            this.zzcqm = zzcqm;
            this.zzacn.zza(this);
        }
    }
    
    public final String zzqm() {
        return this.zzcov;
    }
}
