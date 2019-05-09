package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpb;
import java.util.List;

final class zzbf implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ int zzaag;
    private final /* synthetic */ zzpb zzaaj;
    private final /* synthetic */ List zzaak;

    zzbf(zzbc zzbc, zzpb zzpb, int i, List list) {
        this.zzaaf = zzbc;
        this.zzaaj = zzpb;
        this.zzaag = i;
        this.zzaak = list;
    }

    public final void run() {
        boolean z = true;
        try {
            zzbc zzbc;
            Object zzb;
            if ((this.zzaaj instanceof zzoq) && this.zzaaf.zzvw.zzadg != null) {
                zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                zzbc.zzvu = z;
                zzb = zzbc.zza(this.zzaaj);
                this.zzaaf.zzvw.zzadg.zza(zzb);
                this.zzaaf.zzb(zzb.zzka());
            } else if ((this.zzaaj instanceof zzoq) && this.zzaaf.zzvw.zzadf != null) {
                zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                zzbc.zzvu = z;
                zzoq zzoq = (zzoq) this.zzaaj;
                this.zzaaf.zzvw.zzadf.zza(zzoq);
                this.zzaaf.zzb(zzoq.zzka());
            } else if ((this.zzaaj instanceof zzoo) && this.zzaaf.zzvw.zzadg != null) {
                zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                zzbc.zzvu = z;
                zzb = zzbc.zza(this.zzaaj);
                this.zzaaf.zzvw.zzadg.zza(zzb);
                this.zzaaf.zzb(zzb.zzka());
            } else if (!(this.zzaaj instanceof zzoo) || this.zzaaf.zzvw.zzade == null) {
                zza zza = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                zza.zzc(3, z);
            } else {
                zzbc = this.zzaaf;
                if (this.zzaag == this.zzaak.size() - 1) {
                    z = false;
                }
                zzbc.zzvu = z;
                zzoo zzoo = (zzoo) this.zzaaj;
                this.zzaaf.zzvw.zzade.zza(zzoo);
                this.zzaaf.zzb(zzoo.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
