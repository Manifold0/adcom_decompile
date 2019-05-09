// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import com.google.android.gms.ads.internal.zzbc;
import android.content.Context;
import java.util.concurrent.Future;

@zzadh
public final class zzabt extends zzajx
{
    private final Object mLock;
    private final zzabm zzbzd;
    private final zzaji zzbze;
    private final zzaej zzbzf;
    private final zzabv zzbzu;
    private Future<zzajh> zzbzv;
    
    public zzabt(final Context context, final zzbc zzbc, final zzaji zzaji, final zzci zzci, final zzabm zzabm, final zznx zznx) {
        this(zzaji, zzabm, new zzabv(context, zzbc, new zzalt(context), zzci, zzaji, zznx));
    }
    
    private zzabt(final zzaji zzbze, final zzabm zzbzd, final zzabv zzbzu) {
        this.mLock = new Object();
        this.zzbze = zzbze;
        this.zzbzf = zzbze.zzcos;
        this.zzbzd = zzbzd;
        this.zzbzu = zzbzu;
    }
    
    @Override
    public final void onStop() {
        synchronized (this.mLock) {
            if (this.zzbzv != null) {
                this.zzbzv.cancel(true);
            }
        }
    }
    
    @Override
    public final void zzdn() {
    Label_0046_Outer:
        while (true) {
            int n = -2;
            while (true) {
                while (true) {
                    try {
                        Object mLock = this.mLock;
                        synchronized (mLock) {
                            this.zzbzv = (Future<zzajh>)zzaki.zza((Callable<Object>)this.zzbzu);
                            // monitorexit(mLock)
                            mLock = this.zzbzv.get(60000L, TimeUnit.MILLISECONDS);
                            if (mLock != null) {
                                zzakk.zzcrm.post((Runnable)new zzabu(this, (zzajh)mLock));
                                return;
                            }
                        }
                    }
                    catch (TimeoutException ex) {
                        zzakb.zzdk("Timed out waiting for native ad.");
                        n = 2;
                        this.zzbzv.cancel(true);
                        final Object mLock = null;
                        continue Label_0046_Outer;
                    }
                    catch (ExecutionException ex2) {
                        n = 0;
                        final Object mLock = null;
                        continue Label_0046_Outer;
                    }
                    catch (InterruptedException ex3) {
                        n = 0;
                        final Object mLock = null;
                        continue Label_0046_Outer;
                    }
                    catch (CancellationException ex4) {
                        n = 0;
                        final Object mLock = null;
                        continue Label_0046_Outer;
                    }
                    break;
                }
                Object mLock = new zzajh(this.zzbze.zzcgs.zzccv, null, null, n, null, null, this.zzbzf.orientation, this.zzbzf.zzbsu, this.zzbze.zzcgs.zzccy, false, null, null, null, null, null, this.zzbzf.zzcer, this.zzbze.zzacv, this.zzbzf.zzcep, this.zzbze.zzcoh, this.zzbzf.zzceu, this.zzbzf.zzcev, this.zzbze.zzcob, null, null, null, null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, null, null, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, false, this.zzbze.zzcos.zzcfp, null, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
                continue;
            }
        }
    }
}
