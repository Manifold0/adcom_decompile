package com.google.android.gms.games.internal;

import android.os.Binder;
import android.view.View;
import com.google.android.gms.common.util.PlatformVersion;

public class zzac {
    protected zze zzjc;
    protected zzae zzjd;

    private zzac(zze zze, int i) {
        this.zzjc = zze;
        zzm(i);
    }

    public static zzac zza(zze zze, int i) {
        return PlatformVersion.isAtLeastHoneycombMR1() ? new zzaf(zze, i) : new zzac(zze, i);
    }

    public void zzb(View view) {
    }

    public void zzbj() {
        this.zzjc.zza(this.zzjd.zzjb, this.zzjd.zzbk());
    }

    protected void zzm(int i) {
        this.zzjd = new zzae(i, new Binder());
    }
}
