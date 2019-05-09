// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzxa;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzarg;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzait;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzjn;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaam;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzi extends zzd implements zzaf, zzaam
{
    private boolean zzwl;
    
    public zzi(final Context context, final zzjn zzjn, final String s, final zzxn zzxn, final zzang zzang, final zzw zzw) {
        super(context, zzjn, s, zzxn, zzang, zzw);
    }
    
    protected zzaqw zza(final zzaji zzaji, @Nullable final zzx zzx, @Nullable final zzait zzait) throws zzarg {
        final View nextView = this.zzvw.zzacs.getNextView();
        if (nextView instanceof zzaqw) {
            ((zzaqw)nextView).destroy();
        }
        if (nextView != null) {
            this.zzvw.zzacs.removeView(nextView);
        }
        zzbv.zzel();
        final zzaqw zza = zzarc.zza(this.zzvw.zzrt, zzasi.zzb(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, zzaji.zzcoq);
        if (this.zzvw.zzacv.zzard == null) {
            this.zzg(zza.getView());
        }
        zza.zzuf().zza((zzjd)this, this, this, this, this, false, null, zzx, this, zzait);
        this.zza(zza);
        zza.zzdr(zzaji.zzcgs.zzcdi);
        return zza;
    }
    
    @Override
    public final void zza(final int n, final int n2, final int n3, final int n4) {
        this.zzbp();
    }
    
    @Override
    protected void zza(final zzaji zzaji, final zznx zznx) {
        if (zzaji.errorCode != -2) {
            zzakk.zzcrm.post((Runnable)new zzk(this, zzaji));
            return;
        }
        if (zzaji.zzacv != null) {
            this.zzvw.zzacv = zzaji.zzacv;
        }
        if (zzaji.zzcos.zzceq && !zzaji.zzcos.zzarg) {
            this.zzvw.zzadv = 0;
            final zzbw zzvw = this.zzvw;
            zzbv.zzej();
            zzvw.zzacu = zzabl.zza(this.zzvw.zzrt, this, zzaji, this.zzvw.zzacq, null, this.zzwh, this, zznx);
            return;
        }
        zzakk.zzcrm.post((Runnable)new zzl(this, zzaji, this.zzwc.zzxa.zza(this.zzvw.zzrt, this.zzvw.zzacr, zzaji.zzcos), zznx));
    }
    
    protected final void zza(final zzaqw zzaqw) {
        zzaqw.zza("/trackActiveViewUnit", new zzj(this));
    }
    
    @Override
    public final void zza(final zzod zzado) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzvw.zzado = zzado;
    }
    
    @Override
    protected boolean zza(@Nullable final zzajh zzajh, final zzajh zzajh2) {
        if (this.zzvw.zzfo() && this.zzvw.zzacs != null) {
            this.zzvw.zzacs.zzfr().zzdb(zzajh2.zzcev);
        }
        while (true) {
            try {
                if (zzajh2.zzbyo == null || zzajh2.zzceq || !zzajh2.zzcor || !(boolean)zzkb.zzik().zzd(zznk.zzbfq) || zzajh2.zzccv.extras.containsKey("sdk_less_server_data")) {
                    break Label_0107;
                }
                try {
                    zzajh2.zzbyo.zzus();
                    return super.zza(zzajh, zzajh2);
                }
                catch (Throwable t) {
                    zzakb.v("Could not render test Ad label.");
                }
            }
            catch (RuntimeException ex) {
                zzakb.v("Could not render test AdLabel.");
                continue;
            }
            break;
        }
    }
    
    @VisibleForTesting
    final void zzb(final zzaqw zzaqw) {
        if (this.zzvw.zzacw != null) {
            this.zzvy.zza(this.zzvw.zzacv, this.zzvw.zzacw, zzaqw.getView(), zzaqw);
            this.zzwl = false;
            return;
        }
        this.zzwl = true;
        zzakb.zzdk("Request to enable ActiveView before adState is available.");
    }
    
    @Override
    protected void zzbq() {
        super.zzbq();
        if (this.zzwl && (boolean)zzkb.zzik().zzd(zznk.zzbcb)) {
            this.zzb(this.zzvw.zzacw.zzbyo);
        }
    }
    
    @Override
    public final void zzcn() {
        this.onAdClicked();
    }
    
    @Override
    public final void zzco() {
        this.recordImpression();
        this.zzbm();
    }
    
    protected final boolean zzcp() {
        return this.zzvw.zzacx != null && this.zzvw.zzacx.zzcos != null && this.zzvw.zzacx.zzcos.zzcfp;
    }
    
    @Override
    public final void zzcq() {
        this.zzbn();
    }
    
    @Override
    public final void zzh(final View zzadu) {
        this.zzvw.zzadu = zzadu;
        this.zzb(new zzajh(this.zzvw.zzacx, null, null, null, null, null, null, null));
    }
}
