// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import android.os.IBinder;
import android.os.Binder;
import android.view.View;
import com.google.android.gms.common.util.PlatformVersion;

public class zzac
{
    protected zze zzjc;
    protected zzae zzjd;
    
    private zzac(final zze zzjc, final int n) {
        this.zzjc = zzjc;
        this.zzm(n);
    }
    
    public static zzac zza(final zze zze, final int n) {
        if (PlatformVersion.isAtLeastHoneycombMR1()) {
            return new zzaf(zze, n);
        }
        return new zzac(zze, n);
    }
    
    public void zzb(final View view) {
    }
    
    public void zzbj() {
        this.zzjc.zza(this.zzjd.zzjb, this.zzjd.zzbk());
    }
    
    protected void zzm(final int n) {
        this.zzjd = new zzae(n, (IBinder)new Binder(), null);
    }
}
