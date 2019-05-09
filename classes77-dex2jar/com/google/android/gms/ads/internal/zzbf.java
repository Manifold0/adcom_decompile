// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzqk;
import com.google.android.gms.internal.ads.zzoo;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqo;
import com.google.android.gms.internal.ads.zzrr;
import com.google.android.gms.internal.ads.zzoq;
import java.util.List;
import com.google.android.gms.internal.ads.zzpb;

final class zzbf implements Runnable
{
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ int zzaag;
    private final /* synthetic */ zzpb zzaaj;
    private final /* synthetic */ List zzaak;
    
    zzbf(final zzbc zzaaf, final zzpb zzaaj, final int zzaag, final List zzaak) {
        this.zzaaf = zzaaf;
        this.zzaaj = zzaaj;
        this.zzaag = zzaag;
        this.zzaak = zzaak;
    }
    
    @Override
    public final void run() {
        boolean b2;
        boolean b3;
        boolean b4;
        while (true) {
            final boolean b = true;
            b2 = true;
            b3 = true;
            b4 = true;
            boolean zzvu = true;
            while (true) {
                Label_0442: {
                    while (true) {
                        try {
                            if (this.zzaaj instanceof zzoq && this.zzaaf.zzvw.zzadg != null) {
                                final zzbc zzaaf = this.zzaaf;
                                if (this.zzaag != this.zzaak.size() - 1) {
                                    zzaaf.zzvu = zzvu;
                                    final zzov zzb = zza(this.zzaaj);
                                    this.zzaaf.zzvw.zzadg.zza((zzrr)zzb);
                                    this.zzaaf.zzb(zzb.zzka());
                                    return;
                                }
                                break Label_0442;
                            }
                            else {
                                if (!(this.zzaaj instanceof zzoq) || this.zzaaf.zzvw.zzadf == null) {
                                    break;
                                }
                                final zzbc zzaaf2 = this.zzaaf;
                                if (this.zzaag != this.zzaak.size() - 1) {
                                    final boolean zzvu2 = b;
                                    zzaaf2.zzvu = zzvu2;
                                    final zzoq zzoq = (zzoq)this.zzaaj;
                                    this.zzaaf.zzvw.zzadf.zza((zzqo)zzoq);
                                    this.zzaaf.zzb(zzoq.zzka());
                                    return;
                                }
                            }
                        }
                        catch (RemoteException ex) {
                            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                            return;
                        }
                        final boolean zzvu2 = false;
                        continue;
                    }
                }
                zzvu = false;
                continue;
            }
        }
        if (this.zzaaj instanceof zzoo && this.zzaaf.zzvw.zzadg != null) {
            this.zzaaf.zzvu = (this.zzaag != this.zzaak.size() - 1 && b2);
            final zzov zzb2 = zza(this.zzaaj);
            this.zzaaf.zzvw.zzadg.zza((zzrr)zzb2);
            this.zzaaf.zzb(zzb2.zzka());
            return;
        }
        if (this.zzaaj instanceof zzoo && this.zzaaf.zzvw.zzade != null) {
            this.zzaaf.zzvu = (this.zzaag != this.zzaak.size() - 1 && b3);
            final zzoo zzoo = (zzoo)this.zzaaj;
            this.zzaaf.zzvw.zzade.zza((zzqk)zzoo);
            this.zzaaf.zzb(zzoo.zzka());
            return;
        }
        this.zzaaf.zzc(3, this.zzaag != this.zzaak.size() - 1 && b4);
    }
}
