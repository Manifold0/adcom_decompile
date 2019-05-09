package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.ads.internal.zzbv;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

@zzadh
public final class zzace {
    private final Context mContext;
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private int zzadx = -1;
    @GuardedBy("mLock")
    private int zzady = -1;
    private zzamj zzadz;
    private final DisplayMetrics zzagj;
    private final zzci zzbjc;
    private final zzaji zzbze;
    private final zzbc zzcbc;
    private OnGlobalLayoutListener zzcbd;
    private OnScrollChangedListener zzcbe;
    private final zznx zzvr;

    public zzace(Context context, zzci zzci, zzaji zzaji, zznx zznx, zzbc zzbc) {
        this.mContext = context;
        this.zzbjc = zzci;
        this.zzbze = zzaji;
        this.zzvr = zznx;
        this.zzcbc = zzbc;
        this.zzadz = new zzamj(200);
        zzbv.zzek();
        this.zzagj = zzakk.zza((WindowManager) context.getSystemService("window"));
    }

    private final void zza(WeakReference<zzaqw> weakReference, boolean z) {
        if (weakReference != null) {
            zzaqw zzaqw = (zzaqw) weakReference.get();
            if (zzaqw != null && zzaqw.getView() != null) {
                if (!z || this.zzadz.tryAcquire()) {
                    int[] iArr = new int[2];
                    zzaqw.getView().getLocationOnScreen(iArr);
                    zzkb.zzif();
                    int zzb = zzamu.zzb(this.zzagj, iArr[0]);
                    zzkb.zzif();
                    int zzb2 = zzamu.zzb(this.zzagj, iArr[1]);
                    synchronized (this.mLock) {
                        if (!(this.zzadx == zzb && this.zzady == zzb2)) {
                            this.zzadx = zzb;
                            this.zzady = zzb2;
                            zzaqw.zzuf().zza(this.zzadx, this.zzady, !z);
                        }
                    }
                }
            }
        }
    }

    final /* synthetic */ void zza(zzaoj zzaoj, zzaqw zzaqw, boolean z) {
        this.zzcbc.zzdw();
        zzaoj.set(zzaqw);
    }

    final /* synthetic */ void zza(JSONObject jSONObject, zzaoj zzaoj) {
        try {
            zzbv.zzel();
            zzaqw zza = zzarc.zza(this.mContext, zzasi.zzvq(), "native-video", false, false, this.zzbjc, this.zzbze.zzcgs.zzacr, this.zzvr, null, this.zzcbc.zzbi(), this.zzbze.zzcoq);
            zza.zza(zzasi.zzvr());
            this.zzcbc.zzf(zza);
            WeakReference weakReference = new WeakReference(zza);
            zzasc zzuf = zza.zzuf();
            if (this.zzcbd == null) {
                this.zzcbd = new zzack(this, weakReference);
            }
            OnGlobalLayoutListener onGlobalLayoutListener = this.zzcbd;
            if (this.zzcbe == null) {
                this.zzcbe = new zzacl(this, weakReference);
            }
            zzuf.zza(onGlobalLayoutListener, this.zzcbe);
            zza.zza("/video", zzf.zzblz);
            zza.zza("/videoMeta", zzf.zzbma);
            zza.zza("/precache", new zzaql());
            zza.zza("/delayPageLoaded", zzf.zzbmd);
            zza.zza("/instrument", zzf.zzbmb);
            zza.zza("/log", zzf.zzblu);
            zza.zza("/videoClicked", zzf.zzblv);
            zza.zza("/trackActiveViewUnit", new zzaci(this));
            zza.zza("/untrackActiveViewUnit", new zzacj(this));
            zza.zzuf().zza(new zzacg(zza, jSONObject));
            zza.zzuf().zza(new zzach(this, zzaoj, zza));
            zza.loadUrl((String) zzkb.zzik().zzd(zznk.zzbbs));
        } catch (Throwable e) {
            zzakb.zzc("Exception occurred while getting video view", e);
            zzaoj.set(null);
        }
    }
}
