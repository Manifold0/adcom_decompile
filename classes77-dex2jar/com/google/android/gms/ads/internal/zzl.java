// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.internal.ads.zzabl;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoa;
import com.google.android.gms.internal.ads.zzny;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaji;

final class zzl implements Runnable
{
    final /* synthetic */ zzaji zzwg;
    final /* synthetic */ zzi zzwm;
    final /* synthetic */ zzait zzwn;
    private final /* synthetic */ zznx zzwo;
    
    zzl(final zzi zzwm, final zzaji zzwg, final zzait zzwn, final zznx zzwo) {
        this.zzwm = zzwm;
        this.zzwg = zzwg;
        this.zzwn = zzwn;
        this.zzwo = zzwo;
    }
    
    @Override
    public final void run() {
        if (this.zzwg.zzcos.zzcez && this.zzwm.zzvw.zzado != null) {
            String zzcu = null;
            if (this.zzwg.zzcos.zzbyq != null) {
                zzbv.zzek();
                zzcu = zzakk.zzcu(this.zzwg.zzcos.zzbyq);
            }
            final zzny zzny = new zzny(this.zzwm, zzcu, this.zzwg.zzcos.zzceo);
            this.zzwm.zzvw.zzadv = 1;
            try {
                this.zzwm.zzvu = false;
                this.zzwm.zzvw.zzado.zza((zzoa)zzny);
                return;
            }
            catch (RemoteException ex) {
                zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                this.zzwm.zzvu = true;
            }
        }
        final zzx zzx = new zzx(this.zzwm.zzvw.zzrt, this.zzwn, this.zzwg.zzcos.zzcfi);
        try {
            final zzaqw zza = this.zzwm.zza(this.zzwg, zzx, this.zzwn);
            zza.setOnTouchListener((View$OnTouchListener)new zzn(this, zzx));
            zza.setOnClickListener((View$OnClickListener)new zzo(this, zzx));
            this.zzwm.zzvw.zzadv = 0;
            final zzbw zzvw = this.zzwm.zzvw;
            zzbv.zzej();
            zzvw.zzacu = zzabl.zza(this.zzwm.zzvw.zzrt, this.zzwm, this.zzwg, this.zzwm.zzvw.zzacq, zza, this.zzwm.zzwh, this.zzwm, this.zzwo);
        }
        catch (zzarg zzarg) {
            zzakb.zzb("Could not obtain webview.", (Throwable)zzarg);
            zzakk.zzcrm.post((Runnable)new zzm(this));
        }
    }
}
