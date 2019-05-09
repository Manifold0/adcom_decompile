// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;

@zzadh
public abstract class zzabf implements zzalc<Void>, zzasd
{
    protected final Context mContext;
    protected final zzaqw zzbnd;
    private final zzabm zzbzd;
    private final zzaji zzbze;
    protected zzaej zzbzf;
    private Runnable zzbzg;
    private final Object zzbzh;
    private AtomicBoolean zzbzi;
    
    protected zzabf(final Context mContext, final zzaji zzbze, final zzaqw zzbnd, final zzabm zzbzd) {
        this.zzbzh = new Object();
        this.zzbzi = new AtomicBoolean(true);
        this.mContext = mContext;
        this.zzbze = zzbze;
        this.zzbzf = this.zzbze.zzcos;
        this.zzbnd = zzbnd;
        this.zzbzd = zzbzd;
    }
    
    @Override
    public void cancel() {
        if (!this.zzbzi.getAndSet(false)) {
            return;
        }
        this.zzbnd.stopLoading();
        zzbv.zzem();
        zzakq.zzi(this.zzbnd);
        this.zzz(-1);
        zzakk.zzcrm.removeCallbacks(this.zzbzg);
    }
    
    @Override
    public final void zze(final boolean b) {
        int n = 0;
        zzakb.zzck("WebView finished loading.");
        if (!this.zzbzi.getAndSet(false)) {
            return;
        }
        if (b) {
            n = -2;
        }
        this.zzz(n);
        zzakk.zzcrm.removeCallbacks(this.zzbzg);
    }
    
    protected abstract void zzns();
    
    protected void zzz(final int n) {
        if (n != -2) {
            this.zzbzf = new zzaej(n, this.zzbzf.zzbsu);
        }
        this.zzbnd.zztz();
        final zzabm zzbzd = this.zzbzd;
        final zzaef zzcgs = this.zzbze.zzcgs;
        zzbzd.zzb(new zzajh(zzcgs.zzccv, this.zzbnd, this.zzbzf.zzbsn, n, this.zzbzf.zzbso, this.zzbzf.zzces, this.zzbzf.orientation, this.zzbzf.zzbsu, zzcgs.zzccy, this.zzbzf.zzceq, null, null, null, null, null, this.zzbzf.zzcer, this.zzbze.zzacv, this.zzbzf.zzcep, this.zzbze.zzcoh, this.zzbzf.zzceu, this.zzbzf.zzcev, this.zzbze.zzcob, null, this.zzbzf.zzcfe, this.zzbzf.zzcff, this.zzbzf.zzcfg, this.zzbzf.zzcfh, this.zzbzf.zzcfi, null, this.zzbzf.zzbsr, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, this.zzbze.zzcos.zzcfp, this.zzbzf.zzbsp, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq));
    }
}
