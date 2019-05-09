// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ListIterator;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;
import java.util.List;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzabr extends zzabh
{
    private final zzaqw zzbnd;
    private zzwy zzbtj;
    @VisibleForTesting
    private zzww zzbzq;
    protected zzxe zzbzr;
    private boolean zzbzs;
    private final zznx zzvr;
    private zzxn zzwh;
    
    zzabr(final Context context, final zzaji zzaji, final zzxn zzwh, final zzabm zzabm, final zznx zzvr, final zzaqw zzbnd) {
        super(context, zzaji, zzabm);
        this.zzwh = zzwh;
        this.zzbtj = zzaji.zzcod;
        this.zzvr = zzvr;
        this.zzbnd = zzbnd;
    }
    
    @Override
    public final void onStop() {
        synchronized (this.zzbzh) {
            super.onStop();
            if (this.zzbzq != null) {
                this.zzbzq.cancel();
            }
        }
    }
    
    @Override
    protected final zzajh zzaa(final int n) {
        final zzaef zzcgs = this.zzbze.zzcgs;
        final zzjj zzccv = zzcgs.zzccv;
        final zzaqw zzbnd = this.zzbnd;
        final List<String> zzbsn = this.zzbzf.zzbsn;
        final List<String> zzbso = this.zzbzf.zzbso;
        final List<String> zzces = this.zzbzf.zzces;
        final int orientation = this.zzbzf.orientation;
        final long zzbsu = this.zzbzf.zzbsu;
        final String zzccy = zzcgs.zzccy;
        final boolean zzceq = this.zzbzf.zzceq;
        zzwx zzbtw;
        if (this.zzbzr != null) {
            zzbtw = this.zzbzr.zzbtw;
        }
        else {
            zzbtw = null;
        }
        zzxq zzbtx;
        if (this.zzbzr != null) {
            zzbtx = this.zzbzr.zzbtx;
        }
        else {
            zzbtx = null;
        }
        String s;
        if (this.zzbzr != null) {
            s = this.zzbzr.zzbty;
        }
        else {
            s = AdMobAdapter.class.getName();
        }
        final zzwy zzbtj = this.zzbtj;
        zzxa zzbtz;
        if (this.zzbzr != null) {
            zzbtz = this.zzbzr.zzbtz;
        }
        else {
            zzbtz = null;
        }
        final long zzcer = this.zzbzf.zzcer;
        final zzjn zzacv = this.zzbze.zzacv;
        final long zzcep = this.zzbzf.zzcep;
        final long zzcoh = this.zzbze.zzcoh;
        final long zzceu = this.zzbzf.zzceu;
        final String zzcev = this.zzbzf.zzcev;
        final JSONObject zzcob = this.zzbze.zzcob;
        final zzaig zzcfe = this.zzbzf.zzcfe;
        final List<String> zzcff = this.zzbzf.zzcff;
        final List<String> zzcfg = this.zzbzf.zzcfg;
        final boolean b = this.zzbtj != null && this.zzbtj.zzbsz;
        final zzael zzcfi = this.zzbzf.zzcfi;
        String s2;
        if (this.zzbzq != null) {
            final List<zzxe> zzme = this.zzbzq.zzme();
            if (zzme == null) {
                s2 = "".toString();
            }
            else {
                final Iterator<zzxe> iterator = zzme.iterator();
                String string = "";
                while (iterator.hasNext()) {
                    final zzxe zzxe = iterator.next();
                    if (zzxe != null && zzxe.zzbtw != null && !TextUtils.isEmpty((CharSequence)zzxe.zzbtw.zzbru)) {
                        final String value = String.valueOf(string);
                        final String zzbru = zzxe.zzbtw.zzbru;
                        int n2 = 0;
                        switch (zzxe.zzbtv) {
                            default: {
                                n2 = 6;
                                break;
                            }
                            case 0: {
                                n2 = 0;
                                break;
                            }
                            case 1: {
                                n2 = 1;
                                break;
                            }
                            case 3: {
                                n2 = 2;
                                break;
                            }
                            case 4: {
                                n2 = 3;
                                break;
                            }
                            case -1: {
                                n2 = 4;
                                break;
                            }
                            case 5: {
                                n2 = 5;
                                break;
                            }
                        }
                        final String string2 = new StringBuilder(String.valueOf(zzbru).length() + 33).append(zzbru).append(".").append(n2).append(".").append(zzxe.zzbub).toString();
                        string = new StringBuilder(String.valueOf(value).length() + 1 + String.valueOf(string2).length()).append(value).append(string2).append("_").toString();
                    }
                }
                s2 = string.substring(0, Math.max(0, string.length() - 1));
            }
        }
        else {
            s2 = null;
        }
        return new zzajh(zzccv, zzbnd, zzbsn, n, zzbso, zzces, orientation, zzbsu, zzccy, zzceq, zzbtw, zzbtx, s, zzbtj, zzbtz, zzcer, zzacv, zzcep, zzcoh, zzceu, zzcev, zzcob, null, zzcfe, zzcff, zzcfg, b, zzcfi, s2, this.zzbzf.zzbsr, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbzf.zzzl, this.zzbze.zzcor, this.zzbzf.zzcfp, this.zzbzf.zzbsp, this.zzbzf.zzzm, this.zzbzf.zzcfq);
    }
    
    @Override
    protected final void zze(final long n) throws zzabk {
        Object o = this.zzbzh;
        synchronized (o) {
            zzww zzbzq;
            if (this.zzbtj.zzbsx != -1) {
                zzbzq = new zzxh(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, n, (long)zzkb.zzik().zzd(zznk.zzbao), 2, this.zzbze.zzcor);
            }
            else {
                zzbzq = new zzxk(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, n, (long)zzkb.zzik().zzd(zznk.zzbao), this.zzvr, this.zzbze.zzcor);
            }
            this.zzbzq = zzbzq;
            // monitorexit(o)
            final ArrayList<Object> list = new ArrayList<Object>(this.zzbtj.zzbsm);
            final boolean b = false;
            o = this.zzbze.zzcgs.zzccv.zzaqg;
            int boolean1 = b ? 1 : 0;
            if (o != null) {
                o = ((Bundle)o).getBundle("com.google.ads.mediation.admob.AdMobAdapter");
                boolean1 = (b ? 1 : 0);
                if (o != null) {
                    boolean1 = (((Bundle)o).getBoolean("_skipMediation") ? 1 : 0);
                }
            }
            if (boolean1 != 0) {
                o = list.listIterator();
                while (((ListIterator)o).hasNext()) {
                    if (!((ListIterator<zzwx>)o).next().zzbrt.contains("com.google.ads.mediation.admob.AdMobAdapter")) {
                        ((ListIterator)o).remove();
                    }
                }
            }
        }
        final List<zzwx> list2;
        this.zzbzr = this.zzbzq.zzh(list2);
        switch (this.zzbzr.zzbtv) {
            default: {
                throw new zzabk(new StringBuilder(40).append("Unexpected mediation result: ").append(this.zzbzr.zzbtv).toString(), 0);
            }
            case 1: {
                throw new zzabk("No fill from any mediation ad networks.", 3);
            }
            case 0: {
                if (this.zzbzr.zzbtw != null && this.zzbzr.zzbtw.zzbsf != null) {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    zzakk.zzcrm.post((Runnable)new zzabs(this, countDownLatch));
                    try {
                        countDownLatch.await(10L, TimeUnit.SECONDS);
                        synchronized (this.zzbzh) {
                            if (!this.zzbzs) {
                                throw new zzabk("View could not be prepared", 0);
                            }
                        }
                    }
                    catch (InterruptedException ex) {
                        final String value = String.valueOf(ex);
                        throw new zzabk(new StringBuilder(String.valueOf(value).length() + 38).append("Interrupted while waiting for latch : ").append(value).toString(), 0);
                    }
                    if (this.zzbnd.isDestroyed()) {
                        throw new zzabk("Assets not loaded, web view is destroyed", 0);
                    }
                }
                // monitorexit(o2)
            }
        }
    }
}
