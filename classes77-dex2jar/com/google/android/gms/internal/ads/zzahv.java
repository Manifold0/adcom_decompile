// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;

@zzadh
public final class zzahv extends zzaid
{
    private volatile zzahw zzclm;
    private volatile zzaht zzcma;
    private volatile zzahu zzcmb;
    private volatile zzaia zzcmc;
    
    public zzahv(final zzahu zzcmb) {
        this.zzcmb = zzcmb;
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzaig zzaig) {
        if (this.zzcmb != null) {
            this.zzcmb.zzc(zzaig);
        }
    }
    
    public final void zza(final zzaht zzcma) {
        this.zzcma = zzcma;
    }
    
    public final void zza(final zzahw zzclm) {
        this.zzclm = zzclm;
    }
    
    public final void zza(final zzaia zzcmc) {
        this.zzcmc = zzcmc;
    }
    
    public final void zzc(final Bundle bundle) {
        if (this.zzcmc != null) {
            this.zzcmc.zzc(bundle);
        }
    }
    
    public final void zzc(final IObjectWrapper objectWrapper, final int n) {
        if (this.zzcma != null) {
            this.zzcma.zzac(n);
        }
    }
    
    public final void zzd(final IObjectWrapper objectWrapper, final int n) {
        if (this.zzclm != null) {
            this.zzclm.zza(ObjectWrapper.unwrap(objectWrapper).getClass().getName(), n);
        }
    }
    
    public final void zzq(final IObjectWrapper objectWrapper) {
        if (this.zzcma != null) {
            this.zzcma.zzpc();
        }
    }
    
    public final void zzr(final IObjectWrapper objectWrapper) {
        if (this.zzclm != null) {
            this.zzclm.zzcb(ObjectWrapper.unwrap(objectWrapper).getClass().getName());
        }
    }
    
    public final void zzs(final IObjectWrapper objectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdOpened();
        }
    }
    
    public final void zzt(final IObjectWrapper objectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoStarted();
        }
    }
    
    public final void zzu(final IObjectWrapper objectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdClosed();
        }
    }
    
    public final void zzv(final IObjectWrapper objectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.zzdm();
        }
    }
    
    public final void zzw(final IObjectWrapper objectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdLeftApplication();
        }
    }
    
    public final void zzx(final IObjectWrapper objectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoCompleted();
        }
    }
}
