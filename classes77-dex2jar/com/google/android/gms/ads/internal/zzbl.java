// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import com.google.android.gms.internal.ads.zzakk;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzjj;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbl
{
    private final zzbn zzaan;
    @Nullable
    private zzjj zzaao;
    private boolean zzaap;
    private boolean zzaaq;
    private long zzaar;
    private final Runnable zzy;
    
    public zzbl(final zza zza) {
        this(zza, new zzbn(zzakk.zzcrm));
    }
    
    @VisibleForTesting
    private zzbl(final zza zza, final zzbn zzaan) {
        this.zzaap = false;
        this.zzaaq = false;
        this.zzaar = 0L;
        this.zzaan = zzaan;
        this.zzy = new zzbm(this, new WeakReference((T)zza));
    }
    
    public final void cancel() {
        this.zzaap = false;
        this.zzaan.removeCallbacks(this.zzy);
    }
    
    public final void pause() {
        this.zzaaq = true;
        if (this.zzaap) {
            this.zzaan.removeCallbacks(this.zzy);
        }
    }
    
    public final void resume() {
        this.zzaaq = false;
        if (this.zzaap) {
            this.zzaap = false;
            this.zza(this.zzaao, this.zzaar);
        }
    }
    
    public final void zza(final zzjj zzaao, final long zzaar) {
        if (this.zzaap) {
            zzakb.zzdk("An ad refresh is already scheduled.");
        }
        else {
            this.zzaao = zzaao;
            this.zzaap = true;
            this.zzaar = zzaar;
            if (!this.zzaaq) {
                zzakb.zzdj(new StringBuilder(65).append("Scheduling ad refresh ").append(zzaar).append(" milliseconds from now.").toString());
                this.zzaan.postDelayed(this.zzy, zzaar);
            }
        }
    }
    
    public final void zzdy() {
        this.zzaaq = false;
        this.zzaap = false;
        if (this.zzaao != null && this.zzaao.extras != null) {
            this.zzaao.extras.remove("_ad");
        }
        this.zza(this.zzaao, 0L);
    }
    
    public final boolean zzdz() {
        return this.zzaap;
    }
    
    public final void zzf(final zzjj zzaao) {
        this.zzaao = zzaao;
    }
    
    public final void zzg(final zzjj zzjj) {
        this.zza(zzjj, 60000L);
    }
}
