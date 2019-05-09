// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.security.GeneralSecurityException;
import java.io.UnsupportedEncodingException;
import android.app.Activity;
import android.view.View;
import android.content.Context;
import android.util.DisplayMetrics;
import java.util.LinkedList;
import android.view.MotionEvent;

public abstract class zzcf implements zzce
{
    protected static volatile zzcz zzps;
    protected MotionEvent zzpy;
    protected LinkedList<MotionEvent> zzpz;
    protected long zzqa;
    protected long zzqb;
    protected long zzqc;
    protected long zzqd;
    protected long zzqe;
    protected long zzqf;
    protected long zzqg;
    protected double zzqh;
    private double zzqi;
    private double zzqj;
    protected float zzqk;
    protected float zzql;
    protected float zzqm;
    protected float zzqn;
    private boolean zzqo;
    protected boolean zzqp;
    protected DisplayMetrics zzqq;
    
    static {
        zzcf.zzps = null;
    }
    
    protected zzcf(final Context context) {
        this.zzpz = new LinkedList<MotionEvent>();
        this.zzqa = 0L;
        this.zzqb = 0L;
        this.zzqc = 0L;
        this.zzqd = 0L;
        this.zzqe = 0L;
        this.zzqf = 0L;
        this.zzqg = 0L;
        this.zzqo = false;
        this.zzqp = false;
        try {
            if (zzkb.zzik().zzd(zznk.zzbay)) {
                zzbk.zzv();
            }
            else {
                zzde.zzb(zzcf.zzps);
            }
            this.zzqq = context.getResources().getDisplayMetrics();
        }
        catch (Throwable t) {}
    }
    
    private final String zza(final Context context, final String s, final boolean b, final View view, final Activity activity, final byte[] array) {
        Label_0035: {
            if (!b) {
                break Label_0035;
            }
            try {
                zzba zzba = this.zza(context, view, activity);
                this.zzqo = true;
                while (true) {
                    if (zzba == null || ((zzbfi)zzba).zzacw() == 0) {
                        return Integer.toString(5);
                    }
                    return zzbk.zza(zzba, s);
                    zzba = this.zza(context, null);
                    continue;
                }
            }
            catch (UnsupportedEncodingException ex) {}
            catch (Throwable t) {
                return Integer.toString(3);
            }
            catch (GeneralSecurityException ex2) {
                goto Label_0054;
            }
        }
    }
    
    protected abstract long zza(final StackTraceElement[] p0) throws zzcw;
    
    protected abstract zzba zza(final Context p0, final View p1, final Activity p2);
    
    protected abstract zzba zza(final Context p0, final zzax p1);
    
    @Override
    public final String zza(final Context context) {
        if (zzdg.isMainThread() && (boolean)zzkb.zzik().zzd(zznk.zzbba)) {
            throw new IllegalStateException("The caller must not be called from the UI thread.");
        }
        return this.zza(context, null, false, null, null, null);
    }
    
    @Override
    public final String zza(final Context context, final String s, final View view) {
        return this.zza(context, s, view, null);
    }
    
    @Override
    public final String zza(final Context context, final String s, final View view, final Activity activity) {
        return this.zza(context, s, true, view, activity, null);
    }
    
    @Override
    public final void zza(final int n, final int n2, final int n3) {
        if (this.zzpy != null) {
            this.zzpy.recycle();
        }
        if (this.zzqq != null) {
            this.zzpy = MotionEvent.obtain(0L, (long)n3, 1, n * this.zzqq.density, n2 * this.zzqq.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        }
        else {
            this.zzpy = null;
        }
        this.zzqp = false;
    }
    
    @Override
    public final void zza(final MotionEvent motionEvent) {
        if (this.zzqo) {
            this.zzqd = 0L;
            this.zzqc = 0L;
            this.zzqb = 0L;
            this.zzqa = 0L;
            this.zzqe = 0L;
            this.zzqg = 0L;
            this.zzqf = 0L;
            final Iterator<MotionEvent> iterator = (Iterator<MotionEvent>)this.zzpz.iterator();
            while (iterator.hasNext()) {
                iterator.next().recycle();
            }
            this.zzpz.clear();
            this.zzpy = null;
            this.zzqo = false;
        }
        switch (motionEvent.getAction()) {
            case 0: {
                this.zzqh = 0.0;
                this.zzqi = motionEvent.getRawX();
                this.zzqj = motionEvent.getRawY();
                break;
            }
            case 1:
            case 2: {
                final double zzqi = motionEvent.getRawX();
                final double zzqj = motionEvent.getRawY();
                final double n = zzqi - this.zzqi;
                final double n2 = zzqj - this.zzqj;
                this.zzqh += Math.sqrt(n * n + n2 * n2);
                this.zzqi = zzqi;
                this.zzqj = zzqj;
                break;
            }
        }
        Label_0160: {
            switch (motionEvent.getAction()) {
                case 1: {
                    this.zzpy = MotionEvent.obtain(motionEvent);
                    this.zzpz.add(this.zzpy);
                    if (this.zzpz.size() > 6) {
                        this.zzpz.remove().recycle();
                    }
                    ++this.zzqc;
                    try {
                        this.zzqe = this.zza(new Throwable().getStackTrace());
                    }
                    catch (zzcw zzcw) {}
                    break;
                }
                case 0: {
                    this.zzqk = motionEvent.getX();
                    this.zzql = motionEvent.getY();
                    this.zzqm = motionEvent.getRawX();
                    this.zzqn = motionEvent.getRawY();
                    ++this.zzqa;
                    break;
                }
                case 3: {
                    ++this.zzqd;
                    break;
                }
                case 2: {
                Label_0501_Outer:
                    while (true) {
                        this.zzqb += motionEvent.getHistorySize() + 1;
                        while (true) {
                        Label_0551:
                            while (true) {
                                Label_0545: {
                                    try {
                                        final zzdf zzb = this.zzb(motionEvent);
                                        if (zzb == null || zzb.zzfr == null || zzb.zzst == null) {
                                            break Label_0545;
                                        }
                                        final int n3 = 1;
                                        if (n3 != 0) {
                                            this.zzqf += zzb.zzfr + zzb.zzst;
                                        }
                                        if (this.zzqq == null || zzb == null || zzb.zzfp == null || zzb.zzsu == null) {
                                            break Label_0551;
                                        }
                                        final int n4 = 1;
                                        if (n4 != 0) {
                                            this.zzqg += zzb.zzsu + zzb.zzfp;
                                        }
                                    }
                                    catch (zzcw zzcw2) {}
                                    break Label_0160;
                                }
                                final int n3 = 0;
                                continue Label_0501_Outer;
                            }
                            final int n4 = 0;
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        this.zzqp = true;
    }
    
    protected abstract zzdf zzb(final MotionEvent p0) throws zzcw;
    
    @Override
    public void zzb(final View view) {
    }
}
