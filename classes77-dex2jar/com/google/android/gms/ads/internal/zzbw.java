// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import android.graphics.Rect;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzce;
import java.util.UUID;
import com.google.android.gms.internal.ads.zznk;
import android.content.Context;
import com.google.android.gms.internal.ads.zzamj;
import java.util.HashSet;
import android.view.View;
import com.google.android.gms.internal.ads.zzaju;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzod;
import java.util.List;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzrc;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzalc;
import com.google.android.gms.internal.ads.zzajx;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzci;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbw implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    final String zzaco;
    public String zzacp;
    final zzci zzacq;
    public final zzang zzacr;
    @Nullable
    zzbx zzacs;
    @Nullable
    public zzajx zzact;
    @Nullable
    public zzalc zzacu;
    public zzjn zzacv;
    @Nullable
    public zzajh zzacw;
    public zzaji zzacx;
    @Nullable
    public zzajj zzacy;
    @Nullable
    zzke zzacz;
    @Nullable
    zzkh zzada;
    @Nullable
    zzla zzadb;
    @Nullable
    zzkx zzadc;
    @Nullable
    zzlg zzadd;
    @Nullable
    zzqw zzade;
    @Nullable
    zzqz zzadf;
    @Nullable
    zzrl zzadg;
    SimpleArrayMap<String, zzrc> zzadh;
    SimpleArrayMap<String, zzrf> zzadi;
    zzpl zzadj;
    @Nullable
    zzmu zzadk;
    @Nullable
    zzlu zzadl;
    @Nullable
    zzri zzadm;
    @Nullable
    List<Integer> zzadn;
    @Nullable
    zzod zzado;
    @Nullable
    zzahe zzadp;
    @Nullable
    zzagx zzadq;
    @Nullable
    public String zzadr;
    @Nullable
    List<String> zzads;
    @Nullable
    public zzaju zzadt;
    @Nullable
    View zzadu;
    public int zzadv;
    private HashSet<zzajj> zzadw;
    private int zzadx;
    private int zzady;
    private zzamj zzadz;
    private boolean zzaea;
    private boolean zzaeb;
    private boolean zzaec;
    public final Context zzrt;
    boolean zzze;
    
    public zzbw(final Context context, final zzjn zzjn, final String s, final zzang zzang) {
        this(context, zzjn, s, zzang, null);
    }
    
    private zzbw(final Context zzrt, final zzjn zzacv, final String zzacp, final zzang zzacr, final zzci zzci) {
        this.zzadt = null;
        this.zzadu = null;
        this.zzadv = 0;
        this.zzze = false;
        this.zzadw = null;
        this.zzadx = -1;
        this.zzady = -1;
        this.zzaea = true;
        this.zzaeb = true;
        this.zzaec = false;
        zznk.initialize(zzrt);
        if (zzbv.zzeo().zzpy() != null) {
            final List zzjc = zznk.zzjc();
            if (zzacr.zzcve != 0) {
                zzjc.add(Integer.toString(zzacr.zzcve));
            }
            zzbv.zzeo().zzpy().zzg(zzjc);
        }
        this.zzaco = UUID.randomUUID().toString();
        if (zzacv.zzarc || zzacv.zzare) {
            this.zzacs = null;
        }
        else {
            (this.zzacs = new zzbx(zzrt, zzacp, zzacr.zzcw, (ViewTreeObserver$OnGlobalLayoutListener)this, (ViewTreeObserver$OnScrollChangedListener)this)).setMinimumWidth(zzacv.widthPixels);
            this.zzacs.setMinimumHeight(zzacv.heightPixels);
            this.zzacs.setVisibility(4);
        }
        this.zzacv = zzacv;
        this.zzacp = zzacp;
        this.zzrt = zzrt;
        this.zzacr = zzacr;
        this.zzacq = new zzci(new zzag(this));
        this.zzadz = new zzamj(200L);
        this.zzadi = (SimpleArrayMap<String, zzrf>)new SimpleArrayMap();
    }
    
    private final void zzf(final boolean b) {
        final boolean b2 = true;
        if (this.zzacs != null && this.zzacw != null && this.zzacw.zzbyo != null && this.zzacw.zzbyo.zzuf() != null && (!b || this.zzadz.tryAcquire())) {
            if (this.zzacw.zzbyo.zzuf().zzfz()) {
                final int[] array = new int[2];
                this.zzacs.getLocationOnScreen(array);
                zzkb.zzif();
                final int zzb = zzamu.zzb(this.zzrt, array[0]);
                zzkb.zzif();
                final int zzb2 = zzamu.zzb(this.zzrt, array[1]);
                if (zzb != this.zzadx || zzb2 != this.zzady) {
                    this.zzadx = zzb;
                    this.zzady = zzb2;
                    this.zzacw.zzbyo.zzuf().zza(this.zzadx, this.zzady, !b && b2);
                }
            }
            if (this.zzacs != null) {
                final View viewById = this.zzacs.getRootView().findViewById(16908290);
                if (viewById != null) {
                    final Rect rect = new Rect();
                    final Rect rect2 = new Rect();
                    this.zzacs.getGlobalVisibleRect(rect);
                    viewById.getGlobalVisibleRect(rect2);
                    if (rect.top != rect2.top) {
                        this.zzaea = false;
                    }
                    if (rect.bottom != rect2.bottom) {
                        this.zzaeb = false;
                    }
                }
            }
        }
    }
    
    public final void onGlobalLayout() {
        this.zzf(false);
    }
    
    public final void onScrollChanged() {
        this.zzf(true);
        this.zzaec = true;
    }
    
    public final void zza(final HashSet<zzajj> zzadw) {
        this.zzadw = zzadw;
    }
    
    public final HashSet<zzajj> zzfl() {
        return this.zzadw;
    }
    
    public final void zzfm() {
        if (this.zzacw != null && this.zzacw.zzbyo != null) {
            this.zzacw.zzbyo.destroy();
        }
    }
    
    public final void zzfn() {
        if (this.zzacw == null || this.zzacw.zzbtx == null) {
            return;
        }
        try {
            this.zzacw.zzbtx.destroy();
        }
        catch (RemoteException ex) {
            zzakb.zzdk("Could not destroy mediation adapter.");
        }
    }
    
    public final boolean zzfo() {
        return this.zzadv == 0;
    }
    
    public final boolean zzfp() {
        return this.zzadv == 1;
    }
    
    public final String zzfq() {
        if (this.zzaea && this.zzaeb) {
            return "";
        }
        if (this.zzaea) {
            if (this.zzaec) {
                return "top-scrollable";
            }
            return "top-locked";
        }
        else {
            if (!this.zzaeb) {
                return "";
            }
            if (this.zzaec) {
                return "bottom-scrollable";
            }
            return "bottom-locked";
        }
    }
    
    public final void zzg(final boolean b) {
        if (this.zzadv == 0 && this.zzacw != null && this.zzacw.zzbyo != null) {
            this.zzacw.zzbyo.stopLoading();
        }
        if (this.zzact != null) {
            this.zzact.cancel();
        }
        if (this.zzacu != null) {
            this.zzacu.cancel();
        }
        if (b) {
            this.zzacw = null;
        }
    }
    
    final void zzj(final View view) {
        if (zzkb.zzik().zzd(zznk.zzbat)) {
            final zzce zzaa = this.zzacq.zzaa();
            if (zzaa != null) {
                zzaa.zzb(view);
            }
        }
    }
}
