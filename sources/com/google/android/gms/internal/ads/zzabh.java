package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.SystemClock;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public abstract class zzabh extends zzajx {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzabm zzbzd;
    protected final zzaji zzbze;
    @GuardedBy("mLock")
    protected zzaej zzbzf;
    protected final Object zzbzh = new Object();

    protected zzabh(Context context, zzaji zzaji, zzabm zzabm) {
        super(true);
        this.mContext = context;
        this.zzbze = zzaji;
        this.zzbzf = zzaji.zzcos;
        this.zzbzd = zzabm;
    }

    public void onStop() {
    }

    protected abstract zzajh zzaa(int i);

    public final void zzdn() {
        int errorCode;
        synchronized (this.mLock) {
            zzakb.zzck("AdRendererBackgroundTask started.");
            int i = this.zzbze.errorCode;
            try {
                zze(SystemClock.elapsedRealtime());
            } catch (zzabk e) {
                errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzakb.zzdj(e.getMessage());
                } else {
                    zzakb.zzdk(e.getMessage());
                }
                if (this.zzbzf == null) {
                    this.zzbzf = new zzaej(errorCode);
                } else {
                    this.zzbzf = new zzaej(errorCode, this.zzbzf.zzbsu);
                }
                zzakk.zzcrm.post(new zzabi(this));
                i = errorCode;
            }
            zzakk.zzcrm.post(new zzabj(this, zzaa(i)));
        }
    }

    protected abstract void zze(long j) throws zzabk;
}
