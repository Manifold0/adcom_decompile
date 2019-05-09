// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.view.View;
import com.google.android.gms.internal.ads.zzch;
import com.google.android.gms.internal.ads.zznk;
import java.util.Iterator;
import android.view.MotionEvent;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzkb;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.internal.ads.zzang;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzce;

@zzadh
public final class zzag implements zzce, Runnable
{
    private Context zzrt;
    private final List<Object[]> zzxo;
    private final AtomicReference<zzce> zzxp;
    private zzang zzxq;
    private CountDownLatch zzxr;
    
    private zzag(final Context zzrt, final zzang zzxq) {
        this.zzxo = new Vector<Object[]>();
        this.zzxp = new AtomicReference<zzce>();
        this.zzxr = new CountDownLatch(1);
        this.zzrt = zzrt;
        this.zzxq = zzxq;
        zzkb.zzif();
        if (zzamu.zzsh()) {
            zzaki.zzb(this);
            return;
        }
        this.run();
    }
    
    public zzag(final zzbw zzbw) {
        this(zzbw.zzrt, zzbw.zzacr);
    }
    
    private static Context zzd(final Context context) {
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            return context;
        }
        return applicationContext;
    }
    
    private final boolean zzdc() {
        try {
            this.zzxr.await();
            return true;
        }
        catch (InterruptedException ex) {
            zzakb.zzc("Interrupted during GADSignals creation.", (Throwable)ex);
            return false;
        }
    }
    
    private final void zzdd() {
        if (this.zzxo.isEmpty()) {
            return;
        }
        for (final Object[] array : this.zzxo) {
            if (array.length == 1) {
                this.zzxp.get().zza((MotionEvent)array[0]);
            }
            else {
                if (array.length != 3) {
                    continue;
                }
                this.zzxp.get().zza((int)array[0], (int)array[1], (int)array[2]);
            }
        }
        this.zzxo.clear();
    }
    
    @Override
    public final void run() {
        while (true) {
            while (true) {
                Label_0108: {
                    while (true) {
                        try {
                            if (!this.zzxq.zzcvg) {
                                break Label_0108;
                            }
                            final int n = 1;
                            if (!(boolean)zzkb.zzik().zzd(zznk.zzayl) && n != 0) {
                                final boolean b = true;
                                this.zzxp.set(zzch.zza(this.zzxq.zzcw, zzd(this.zzrt), b));
                                return;
                            }
                        }
                        finally {
                            this.zzxr.countDown();
                            this.zzrt = null;
                            this.zzxq = null;
                        }
                        final boolean b = false;
                        continue;
                    }
                }
                final int n = 0;
                continue;
            }
        }
    }
    
    @Override
    public final String zza(final Context context) {
        if (this.zzdc()) {
            final zzce zzce = this.zzxp.get();
            if (zzce != null) {
                this.zzdd();
                return zzce.zza(zzd(context));
            }
        }
        return "";
    }
    
    @Override
    public final String zza(final Context context, final String s, final View view) {
        return this.zza(context, s, view, null);
    }
    
    @Override
    public final String zza(final Context context, final String s, final View view, final Activity activity) {
        if (this.zzdc()) {
            final zzce zzce = this.zzxp.get();
            if (zzce != null) {
                this.zzdd();
                return zzce.zza(zzd(context), s, view, activity);
            }
        }
        return "";
    }
    
    @Override
    public final void zza(final int n, final int n2, final int n3) {
        final zzce zzce = this.zzxp.get();
        if (zzce != null) {
            this.zzdd();
            zzce.zza(n, n2, n3);
            return;
        }
        this.zzxo.add(new Object[] { n, n2, n3 });
    }
    
    @Override
    public final void zza(final MotionEvent motionEvent) {
        final zzce zzce = this.zzxp.get();
        if (zzce != null) {
            this.zzdd();
            zzce.zza(motionEvent);
            return;
        }
        this.zzxo.add(new Object[] { motionEvent });
    }
    
    @Override
    public final void zzb(final View view) {
        final zzce zzce = this.zzxp.get();
        if (zzce != null) {
            zzce.zzb(view);
        }
    }
}
