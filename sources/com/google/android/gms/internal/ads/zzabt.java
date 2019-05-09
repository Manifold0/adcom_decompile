package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbc;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzadh
public final class zzabt extends zzajx {
    private final Object mLock;
    private final zzabm zzbzd;
    private final zzaji zzbze;
    private final zzaej zzbzf;
    private final zzabv zzbzu;
    private Future<zzajh> zzbzv;

    public zzabt(Context context, zzbc zzbc, zzaji zzaji, zzci zzci, zzabm zzabm, zznx zznx) {
        this(zzaji, zzabm, new zzabv(context, zzbc, new zzalt(context), zzci, zzaji, zznx));
    }

    private zzabt(zzaji zzaji, zzabm zzabm, zzabv zzabv) {
        this.mLock = new Object();
        this.zzbze = zzaji;
        this.zzbzf = zzaji.zzcos;
        this.zzbzd = zzabm;
        this.zzbzu = zzabv;
    }

    public final void onStop() {
        synchronized (this.mLock) {
            if (this.zzbzv != null) {
                this.zzbzv.cancel(true);
            }
        }
    }

    public final void zzdn() {
        zzajh zzajh;
        int i = -2;
        try {
            synchronized (this.mLock) {
                this.zzbzv = zzaki.zza(this.zzbzu);
            }
            zzajh = (zzajh) this.zzbzv.get(60000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            zzakb.zzdk("Timed out waiting for native ad.");
            i = 2;
            this.zzbzv.cancel(true);
            zzajh = null;
        } catch (ExecutionException e2) {
            i = 0;
            zzajh = null;
        } catch (InterruptedException e3) {
            i = 0;
            zzajh = null;
        } catch (CancellationException e4) {
            i = 0;
            zzajh = null;
        }
        if (zzajh == null) {
            zzajh = new zzajh(this.zzbze.zzcgs.zzccv, null, null, i, null, null, this.zzbzf.orientation, this.zzbzf.zzbsu, this.zzbze.zzcgs.zzccy, false, null, null, null, null, null, this.zzbzf.zzcer, this.zzbze.zzacv, this.zzbzf.zzcep, this.zzbze.zzcoh, this.zzbzf.zzceu, this.zzbzf.zzcev, this.zzbze.zzcob, null, null, null, null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, null, null, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, false, this.zzbze.zzcos.zzcfp, null, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
        }
        zzakk.zzcrm.post(new zzabu(this, zzajh));
    }
}
