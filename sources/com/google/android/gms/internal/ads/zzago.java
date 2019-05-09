package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzbw;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Map;

@zzadh
public final class zzago {
    private static final zzxm zzcku = new zzxm();
    private final zzxn zzckv;
    private final zzbw zzckw;
    private final Map<String, zzaib> zzckx = new HashMap();
    private final zzahu zzcky;
    private final zzb zzckz;
    private final zzabm zzcla;

    public zzago(zzbw zzbw, zzxn zzxn, zzahu zzahu, zzb zzb, zzabm zzabm) {
        this.zzckw = zzbw;
        this.zzckv = zzxn;
        this.zzcky = zzahu;
        this.zzckz = zzb;
        this.zzcla = zzabm;
    }

    public static boolean zza(zzajh zzajh, zzajh zzajh2) {
        return true;
    }

    public final void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = (zzaib) this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().destroy();
                }
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onContextChanged(@NonNull Context context) {
        for (zzaib zzpe : this.zzckx.values()) {
            try {
                zzpe.zzpe().zzi(ObjectWrapper.wrap(context));
            } catch (Throwable e) {
                zzakb.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = (zzaib) this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().pause();
                }
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = (zzaib) this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().resume();
                }
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    @Nullable
    public final zzaib zzca(String str) {
        zzaib zzaib;
        Throwable th;
        String str2;
        String valueOf;
        zzaib zzaib2 = (zzaib) this.zzckx.get(str);
        if (zzaib2 != null) {
            return zzaib2;
        }
        try {
            zzaib = new zzaib(("com.google.ads.mediation.admob.AdMobAdapter".equals(str) ? zzcku : this.zzckv).zzbm(str), this.zzcky);
            try {
                this.zzckx.put(str, zzaib);
                return zzaib;
            } catch (Throwable e) {
                th = e;
                str2 = "Fail to instantiate adapter ";
                valueOf = String.valueOf(str);
                zzakb.zzc(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
                return zzaib;
            }
        } catch (Throwable e2) {
            th = e2;
            zzaib = zzaib2;
            str2 = "Fail to instantiate adapter ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            zzakb.zzc(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
            return zzaib;
        }
    }

    public final zzaig zzd(zzaig zzaig) {
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzcod == null || TextUtils.isEmpty(this.zzckw.zzacw.zzcod.zzbsv))) {
            zzaig = new zzaig(this.zzckw.zzacw.zzcod.zzbsv, this.zzckw.zzacw.zzcod.zzbsw);
        }
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzbtw == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw.zzbtw.zzbsd, this.zzckw.zzadr, zzaig);
        }
        return zzaig;
    }

    public final zzb zzos() {
        return this.zzckz;
    }

    public final zzabm zzot() {
        return this.zzcla;
    }

    public final void zzou() {
        this.zzckw.zzadv = 0;
        zzbw zzbw = this.zzckw;
        zzbv.zzej();
        zzalc zzahx = new zzahx(this.zzckw.zzrt, this.zzckw.zzacx, this);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(zzahx.getClass().getName());
        zzakb.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzahx.zznt();
        zzbw.zzacu = zzahx;
    }

    public final void zzov() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbsc);
        }
    }

    public final void zzow() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbse);
        }
    }

    public final void zzw(boolean z) {
        zzaib zzca = zzca(this.zzckw.zzacw.zzbty);
        if (zzca != null && zzca.zzpe() != null) {
            try {
                zzca.zzpe().setImmersiveMode(z);
                zzca.zzpe().showVideo();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }
}
