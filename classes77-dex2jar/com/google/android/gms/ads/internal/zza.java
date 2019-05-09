// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.view.ViewParent;
import android.view.View;
import org.json.JSONException;
import com.google.android.gms.internal.ads.zzane;
import org.json.JSONObject;
import java.util.Collection;
import com.google.android.gms.internal.ads.zzms;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzna;
import com.google.android.gms.internal.ads.zzamu;
import android.location.Location;
import com.google.android.gms.internal.ads.zzjk;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.internal.ads.zzaju;
import java.util.Iterator;
import com.google.android.gms.internal.ads.zzajb;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzajj;
import java.util.HashSet;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzhu;
import com.google.android.gms.internal.ads.zzht;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzagu;
import com.google.android.gms.internal.ads.zzagp;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzaaw;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.Timer;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzajz;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzes;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zznv;
import com.google.android.gms.internal.ads.zznx;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzajs;
import com.google.android.gms.internal.ads.zzadj;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.internal.ads.zzkt;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zza extends zzkt implements zzb, zzd, zzt, zzabm, zzadj, zzajs, zzjd
{
    protected zznx zzvr;
    protected zznv zzvs;
    private zznv zzvt;
    protected boolean zzvu;
    protected final zzbl zzvv;
    protected final zzbw zzvw;
    @Nullable
    protected transient zzjj zzvx;
    protected final zzes zzvy;
    private final Bundle zzvz;
    private boolean zzwa;
    @Nullable
    protected IObjectWrapper zzwb;
    protected final zzw zzwc;
    
    @VisibleForTesting
    zza(final zzbw zzvw, @Nullable final zzbl zzbl, final zzw zzwc) {
        this.zzvu = false;
        this.zzvz = new Bundle();
        this.zzwa = false;
        this.zzvw = zzvw;
        this.zzvv = new zzbl(this);
        this.zzwc = zzwc;
        zzbv.zzek().zzak(this.zzvw.zzrt);
        zzbv.zzek().zzal(this.zzvw.zzrt);
        zzajz.zzai(this.zzvw.zzrt);
        zzbv.zzfi().initialize(this.zzvw.zzrt);
        zzbv.zzeo().zzd(this.zzvw.zzrt, this.zzvw.zzacr);
        zzbv.zzeq().initialize(this.zzvw.zzrt);
        this.zzvy = zzbv.zzeo().zzqd();
        zzbv.zzen().initialize(this.zzvw.zzrt);
        zzbv.zzfk().initialize(this.zzvw.zzrt);
        if (zzkb.zzik().zzd(zznk.zzbci)) {
            final Timer timer = new Timer();
            timer.schedule(new com.google.android.gms.ads.internal.zzb(this, new CountDownLatch((int)zzkb.zzik().zzd(zznk.zzbck)), timer), 0L, (long)zzkb.zzik().zzd(zznk.zzbcj));
        }
    }
    
    protected static boolean zza(final zzjj zzjj) {
        final Bundle bundle = zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle == null || !bundle.containsKey("gw");
    }
    
    @VisibleForTesting
    private static long zzq(final String ex) {
        final int index = ((String)ex).indexOf("ufe");
        int n;
        if ((n = ((String)ex).indexOf(44, index)) == -1) {
            n = ((String)ex).length();
        }
        try {
            return Long.parseLong(((String)ex).substring(index + 4, n));
        }
        catch (IndexOutOfBoundsException ex2) {}
        catch (NumberFormatException ex) {
            goto Label_0044;
        }
    }
    
    public void destroy() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: destroy");
        this.zzvv.cancel();
        this.zzvy.zzi(this.zzvw.zzacw);
        final zzbw zzvw = this.zzvw;
        if (zzvw.zzacs != null) {
            zzvw.zzacs.zzfs();
        }
        zzvw.zzada = null;
        zzvw.zzadc = null;
        zzvw.zzadb = null;
        zzvw.zzado = null;
        zzvw.zzadd = null;
        zzvw.zzg(false);
        if (zzvw.zzacs != null) {
            zzvw.zzacs.removeAllViews();
        }
        zzvw.zzfm();
        zzvw.zzfn();
        zzvw.zzacw = null;
    }
    
    public String getAdUnitId() {
        return this.zzvw.zzacp;
    }
    
    public zzlo getVideoController() {
        return null;
    }
    
    public final boolean isLoading() {
        return this.zzvu;
    }
    
    public final boolean isReady() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: isLoaded");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }
    
    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("Ad state was null when trying to ping click URLs.");
        }
        else {
            zzakb.zzck("Pinging click URLs.");
            if (this.zzvw.zzacy != null) {
                this.zzvw.zzacy.zzpn();
            }
            if (this.zzvw.zzacw.zzbsn != null) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzc(this.zzvw.zzacw.zzbsn));
            }
            if (this.zzvw.zzacz != null) {
                try {
                    this.zzvw.zzacz.onAdClicked();
                }
                catch (RemoteException ex) {
                    zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                }
            }
        }
    }
    
    public final void onAppEvent(final String s, @Nullable final String s2) {
        if (this.zzvw.zzadb == null) {
            return;
        }
        try {
            this.zzvw.zzadb.onAppEvent(s, s2);
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public void pause() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: pause");
    }
    
    public void resume() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: resume");
    }
    
    public void setImmersiveMode(final boolean b) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }
    
    public void setManualImpressionsEnabled(final boolean b) {
        zzakb.zzdk("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }
    
    public final void setUserId(final String zzadr) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setUserId");
        this.zzvw.zzadr = zzadr;
    }
    
    public final void stopLoading() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: stopLoading");
        this.zzvu = false;
        this.zzvw.zzg(true);
    }
    
    public void zza(final zzaaw zzaaw) {
        zzakb.zzdk("#006 Unexpected call to a deprecated method.");
    }
    
    public final void zza(final zzabc zzabc, final String s) {
        zzakb.zzdk("#006 Unexpected call to a deprecated method.");
    }
    
    public final void zza(final zzagx zzadq) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzvw.zzadq = zzadq;
    }
    
    public final void zza(final zzahe zzadp) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedVideoAdListener");
        this.zzvw.zzadp = zzadp;
    }
    
    protected final void zza(@Nullable final zzaig zzaig) {
        if (this.zzvw.zzadp != null) {
            String type = "";
            int zzcmk = 1;
            Label_0030: {
                if (zzaig == null) {
                    break Label_0030;
                }
                try {
                    type = zzaig.type;
                    zzcmk = zzaig.zzcmk;
                    final zzagp zzagp = new zzagp(type, zzcmk);
                    this.zzvw.zzadp.zza((zzagu)zzagp);
                    if (this.zzvw.zzadq != null) {
                        this.zzvw.zzadq.zza((zzagu)zzagp, this.zzvw.zzacx.zzcgs.zzcdi);
                    }
                }
                catch (RemoteException ex) {
                    zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                }
            }
        }
    }
    
    public final void zza(final zzaji zzacx) {
        if (zzacx.zzcos.zzceu != -1L && !TextUtils.isEmpty((CharSequence)zzacx.zzcos.zzcfd)) {
            final long zzq = zzq(zzacx.zzcos.zzcfd);
            if (zzq != -1L) {
                this.zzvr.zza(this.zzvr.zzd(zzq + zzacx.zzcos.zzceu), "stc");
            }
        }
        this.zzvr.zzan(zzacx.zzcos.zzcfd);
        this.zzvr.zza(this.zzvs, "arf");
        this.zzvt = this.zzvr.zzjj();
        this.zzvr.zze("gqi", zzacx.zzcos.zzamj);
        this.zzvw.zzact = null;
        this.zzvw.zzacx = zzacx;
        zzacx.zzcoq.zza(new zzc(this, zzacx));
        zzacx.zzcoq.zza(zzhu.zza.zzb.zzakk);
        this.zza(zzacx, this.zzvr);
    }
    
    protected abstract void zza(final zzaji p0, final zznx p1);
    
    public final void zza(final zzjn zzacv) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdSize");
        this.zzvw.zzacv = zzacv;
        if (this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null && this.zzvw.zzadv == 0) {
            this.zzvw.zzacw.zzbyo.zza(zzasi.zzb(zzacv));
        }
        if (this.zzvw.zzacs == null) {
            return;
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.removeView(this.zzvw.zzacs.getNextView());
        }
        this.zzvw.zzacs.setMinimumWidth(zzacv.widthPixels);
        this.zzvw.zzacs.setMinimumHeight(zzacv.heightPixels);
        this.zzvw.zzacs.requestLayout();
    }
    
    public final void zza(final zzke zzacz) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdClickListener");
        this.zzvw.zzacz = zzacz;
    }
    
    public final void zza(final zzkh zzada) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdListener");
        this.zzvw.zzada = zzada;
    }
    
    public final void zza(final zzkx zzadc) {
        this.zzvw.zzadc = zzadc;
    }
    
    public final void zza(final zzla zzadb) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAppEventListener");
        this.zzvw.zzadb = zzadb;
    }
    
    public final void zza(final zzlg zzadd) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCorrelationIdProvider");
        this.zzvw.zzadd = zzadd;
    }
    
    public final void zza(@Nullable final zzlu zzadl) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setIconAdOptions");
        this.zzvw.zzadl = zzadl;
    }
    
    public final void zza(@Nullable final zzmu zzadk) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setVideoOptions");
        this.zzvw.zzadk = zzadk;
    }
    
    public final void zza(final zznv zznv) {
        this.zzvr = new zznx((boolean)zzkb.zzik().zzd(zznk.zzawh), "load_ad", this.zzvw.zzacv.zzarb);
        this.zzvt = new zznv(-1L, null, null);
        if (zznv == null) {
            this.zzvs = new zznv(-1L, null, null);
            return;
        }
        this.zzvs = new zznv(zznv.getTime(), zznv.zzjg(), zznv.zzjh());
    }
    
    public void zza(final zzod zzod) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }
    
    public final void zza(final String s, final Bundle bundle) {
        this.zzvz.putAll(bundle);
        if (!this.zzwa || this.zzvw.zzadc == null) {
            return;
        }
        try {
            this.zzvw.zzadc.zzt();
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void zza(final HashSet<zzajj> set) {
        this.zzvw.zza(set);
    }
    
    boolean zza(final zzajh zzajh) {
        return false;
    }
    
    protected abstract boolean zza(@Nullable final zzajh p0, final zzajh p1);
    
    protected abstract boolean zza(final zzjj p0, final zznx p1);
    
    protected final List<String> zzb(final List<String> list) {
        final ArrayList<String> list2 = new ArrayList<String>(list.size());
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(zzajb.zzc(iterator.next(), this.zzvw.zzrt));
        }
        return list2;
    }
    
    public void zzb(final zzajh zzacw) {
        this.zzvr.zza(this.zzvt, "awr");
        this.zzvw.zzacu = null;
        if (zzacw.errorCode != -2 && zzacw.errorCode != 3 && this.zzvw.zzfl() != null) {
            zzbv.zzep().zzb(this.zzvw.zzfl());
        }
        if (zzacw.errorCode == -1) {
            this.zzvu = false;
        }
        else {
            if (this.zza(zzacw)) {
                zzakb.zzck("Ad refresh scheduled.");
            }
            if (zzacw.errorCode != -2) {
                if (zzacw.errorCode == 3) {
                    zzacw.zzcoq.zza(zzhu.zza.zzb.zzakm);
                }
                else {
                    zzacw.zzcoq.zza(zzhu.zza.zzb.zzakl);
                }
                this.zzi(zzacw.errorCode);
                return;
            }
            if (this.zzvw.zzadt == null) {
                this.zzvw.zzadt = new zzaju(this.zzvw.zzacp);
            }
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzfr().zzdc(zzacw.zzcfl);
            }
            this.zzvy.zzh(this.zzvw.zzacw);
            if (this.zza(this.zzvw.zzacw, zzacw)) {
                this.zzvw.zzacw = zzacw;
                final zzbw zzvw = this.zzvw;
                if (zzvw.zzacy != null) {
                    if (zzvw.zzacw != null) {
                        zzvw.zzacy.zzh(zzvw.zzacw.zzcoh);
                        zzvw.zzacy.zzi(zzvw.zzacw.zzcoi);
                        zzvw.zzacy.zzz(zzvw.zzacw.zzceq);
                    }
                    zzvw.zzacy.zzy(zzvw.zzacv.zzarc);
                }
                final zznx zzvr = this.zzvr;
                String s;
                if (this.zzvw.zzacw.zzfz()) {
                    s = "1";
                }
                else {
                    s = "0";
                }
                zzvr.zze("is_mraid", s);
                final zznx zzvr2 = this.zzvr;
                String s2;
                if (this.zzvw.zzacw.zzceq) {
                    s2 = "1";
                }
                else {
                    s2 = "0";
                }
                zzvr2.zze("is_mediation", s2);
                if (this.zzvw.zzacw.zzbyo != null && this.zzvw.zzacw.zzbyo.zzuf() != null) {
                    final zznx zzvr3 = this.zzvr;
                    String s3;
                    if (this.zzvw.zzacw.zzbyo.zzuf().zzux()) {
                        s3 = "1";
                    }
                    else {
                        s3 = "0";
                    }
                    zzvr3.zze("is_delay_pl", s3);
                }
                this.zzvr.zza(this.zzvs, "ttc");
                if (zzbv.zzeo().zzpy() != null) {
                    zzbv.zzeo().zzpy().zza(this.zzvr);
                }
                this.zzbv();
                if (this.zzvw.zzfo()) {
                    this.zzbq();
                }
            }
            if (zzacw.zzbsr != null) {
                zzbv.zzek().zza(this.zzvw.zzrt, zzacw.zzbsr);
            }
        }
    }
    
    protected void zzb(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: iload_1        
        //     8: putfield        com/google/android/gms/ads/internal/zza.zzvu:Z
        //    11: aload_0        
        //    12: iconst_1       
        //    13: putfield        com/google/android/gms/ads/internal/zza.zzwa:Z
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    20: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    23: ifnull          38
        //    26: aload_0        
        //    27: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    30: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    33: invokeinterface com/google/android/gms/internal/ads/zzkh.onAdLoaded:()V
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    42: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    45: ifnull          60
        //    48: aload_0        
        //    49: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    52: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    55: invokeinterface com/google/android/gms/internal/ads/zzahe.onRewardedVideoAdLoaded:()V
        //    60: aload_0        
        //    61: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    64: getfield        com/google/android/gms/ads/internal/zzbw.zzadc:Lcom/google/android/gms/internal/ads/zzkx;
        //    67: ifnull          82
        //    70: aload_0        
        //    71: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    74: getfield        com/google/android/gms/ads/internal/zzbw.zzadc:Lcom/google/android/gms/internal/ads/zzkx;
        //    77: invokeinterface com/google/android/gms/internal/ads/zzkx.zzt:()V
        //    82: return         
        //    83: astore_2       
        //    84: ldc_w           "#007 Could not call remote method."
        //    87: aload_2        
        //    88: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    91: goto            38
        //    94: astore_2       
        //    95: ldc_w           "#007 Could not call remote method."
        //    98: aload_2        
        //    99: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   102: goto            60
        //   105: astore_2       
        //   106: ldc_w           "#007 Could not call remote method."
        //   109: aload_2        
        //   110: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   113: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  26     38     83     94     Landroid/os/RemoteException;
        //  48     60     94     105    Landroid/os/RemoteException;
        //  70     82     105    114    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 48, Size: 48
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean zzb(zzjj zzvx) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: loadAd");
        zzbv.zzeq().zzhh();
        this.zzvz.clear();
        this.zzwa = false;
        final zzna zzayo = zznk.zzayo;
        zzjj zzjj = zzvx;
        if (zzkb.zzik().zzd(zzayo)) {
            zzvx = zzvx.zzhv();
            final zzna zzayp = zznk.zzayp;
            zzjj = zzvx;
            if (zzkb.zzik().zzd(zzayp)) {
                zzvx.extras.putBoolean("_newBundle", true);
                zzjj = zzvx;
            }
        }
        zzvx = zzjj;
        if (DeviceProperties.isSidewinder(this.zzvw.zzrt)) {
            zzvx = zzjj;
            if (zzjj.zzaqe != null) {
                zzvx = new zzjk(zzjj).zza((Location)null).zzhw();
            }
        }
        if (this.zzvw.zzact != null || this.zzvw.zzacu != null) {
            if (this.zzvx != null) {
                zzakb.zzdk("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
            }
            else {
                zzakb.zzdk("Loading already in progress, saving this object for future refreshes.");
            }
            this.zzvx = zzvx;
            return false;
        }
        zzakb.zzdj("Starting ad request.");
        this.zza((zznv)null);
        this.zzvs = this.zzvr.zzjj();
        if (zzvx.zzapz) {
            zzakb.zzdj("This request is sent from a test device.");
        }
        else {
            zzkb.zzif();
            final String zzbc = zzamu.zzbc(this.zzvw.zzrt);
            zzakb.zzdj(new StringBuilder(String.valueOf(zzbc).length() + 71).append("Use AdRequest.Builder.addTestDevice(\"").append(zzbc).append("\") to get test ads on this device.").toString());
        }
        this.zzvv.zzf(zzvx);
        return this.zzvu = this.zza(zzvx, this.zzvr);
    }
    
    public final Bundle zzba() {
        if (this.zzwa) {
            return this.zzvz;
        }
        return new Bundle();
    }
    
    public final zzw zzbi() {
        return this.zzwc;
    }
    
    public final IObjectWrapper zzbj() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdFrame");
        return ObjectWrapper.wrap((Object)this.zzvw.zzacs);
    }
    
    @Nullable
    public final zzjn zzbk() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdSize");
        if (this.zzvw.zzacv == null) {
            return null;
        }
        return (zzjn)new zzms(this.zzvw.zzacv);
    }
    
    public final void zzbl() {
        this.zzbo();
    }
    
    public final void zzbm() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: recordManualImpression");
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("Ad state was null when trying to ping manual tracking URLs.");
        }
        else {
            zzakb.zzck("Pinging manual tracking URLs.");
            if (!this.zzvw.zzacw.zzcoo) {
                final ArrayList<Object> list = new ArrayList<Object>();
                if (this.zzvw.zzacw.zzces != null) {
                    list.addAll(this.zzvw.zzacw.zzces);
                }
                if (this.zzvw.zzacw.zzbtw != null && this.zzvw.zzacw.zzbtw.zzbrz != null) {
                    list.addAll(this.zzvw.zzacw.zzbtw.zzbrz);
                }
                if (!list.isEmpty()) {
                    zzbv.zzek();
                    zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, (List<String>)list);
                    this.zzvw.zzacw.zzcoo = true;
                }
            }
        }
    }
    
    protected void zzbn() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    10: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    13: ifnull          28
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    20: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    23: invokeinterface com/google/android/gms/internal/ads/zzkh.onAdClosed:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    32: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    35: ifnull          50
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    42: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    45: invokeinterface com/google/android/gms/internal/ads/zzahe.onRewardedVideoAdClosed:()V
        //    50: return         
        //    51: astore_1       
        //    52: ldc_w           "#007 Could not call remote method."
        //    55: aload_1        
        //    56: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    59: goto            28
        //    62: astore_1       
        //    63: ldc_w           "#007 Could not call remote method."
        //    66: aload_1        
        //    67: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    70: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  16     28     51     62     Landroid/os/RemoteException;
        //  38     50     62     71     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected final void zzbo() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    10: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    13: ifnull          28
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    20: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    23: invokeinterface com/google/android/gms/internal/ads/zzkh.onAdLeftApplication:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    32: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    35: ifnull          50
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    42: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    45: invokeinterface com/google/android/gms/internal/ads/zzahe.onRewardedVideoAdLeftApplication:()V
        //    50: return         
        //    51: astore_1       
        //    52: ldc_w           "#007 Could not call remote method."
        //    55: aload_1        
        //    56: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    59: goto            28
        //    62: astore_1       
        //    63: ldc_w           "#007 Could not call remote method."
        //    66: aload_1        
        //    67: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    70: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  16     28     51     62     Landroid/os/RemoteException;
        //  38     50     62     71     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected final void zzbp() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    10: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    13: ifnull          28
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    20: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    23: invokeinterface com/google/android/gms/internal/ads/zzkh.onAdOpened:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    32: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    35: ifnull          50
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    42: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    45: invokeinterface com/google/android/gms/internal/ads/zzahe.onRewardedVideoAdOpened:()V
        //    50: return         
        //    51: astore_1       
        //    52: ldc_w           "#007 Could not call remote method."
        //    55: aload_1        
        //    56: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    59: goto            28
        //    62: astore_1       
        //    63: ldc_w           "#007 Could not call remote method."
        //    66: aload_1        
        //    67: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    70: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  16     28     51     62     Landroid/os/RemoteException;
        //  38     50     62     71     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void zzbq() {
        this.zzb(false);
    }
    
    public final void zzbr() {
        zzakb.zzdj("Ad impression.");
        if (this.zzvw.zzada == null) {
            return;
        }
        try {
            this.zzvw.zzada.onAdImpression();
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void zzbs() {
        zzakb.zzdj("Ad clicked.");
        if (this.zzvw.zzada == null) {
            return;
        }
        try {
            this.zzvw.zzada.onAdClicked();
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    protected final void zzbt() {
        if (this.zzvw.zzadp == null) {
            return;
        }
        try {
            this.zzvw.zzadp.onRewardedVideoStarted();
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    protected final void zzbu() {
        if (this.zzvw.zzadp == null) {
            return;
        }
        try {
            this.zzvw.zzadp.onRewardedVideoCompleted();
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void zzbv() {
        final zzajh zzacw = this.zzvw.zzacw;
        if (zzacw == null || TextUtils.isEmpty((CharSequence)zzacw.zzcfl) || zzacw.zzcop || !zzbv.zzeu().zzrx()) {
            return;
        }
        zzakb.zzck("Sending troubleshooting signals to the server.");
        zzbv.zzeu().zzb(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzacw.zzcfl, this.zzvw.zzacp);
        zzacw.zzcop = true;
    }
    
    public final zzla zzbw() {
        return this.zzvw.zzadb;
    }
    
    public final zzkh zzbx() {
        return this.zzvw.zzada;
    }
    
    protected final void zzby() {
        if (this.zzwb != null) {
            zzbv.zzfa().zzn(this.zzwb);
            this.zzwb = null;
        }
    }
    
    @Nullable
    protected final String zzbz() {
        final zzaji zzacx = this.zzvw.zzacx;
        if (zzacx == null) {
            return "javascript";
        }
        if (zzacx.zzcos == null) {
            return "javascript";
        }
        final String zzcfq = zzacx.zzcos.zzcfq;
        if (TextUtils.isEmpty((CharSequence)zzcfq)) {
            return "javascript";
        }
        try {
            if (new JSONObject(zzcfq).optInt("media_type", -1) == 0) {
                return null;
            }
            return "javascript";
        }
        catch (JSONException ex) {
            zzane.zzc("", (Throwable)ex);
            return "javascript";
        }
    }
    
    protected final List<String> zzc(final List<String> list) {
        final ArrayList<String> list2 = new ArrayList<String>(list.size());
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(zzajb.zzb(iterator.next(), this.zzvw.zzrt));
        }
        return list2;
    }
    
    protected void zzc(final int p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: bipush          30
        //     6: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //     9: ldc_w           "Failed to load ad: "
        //    12: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    15: iload_1        
        //    16: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    19: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    22: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //    25: aload_0        
        //    26: iload_2        
        //    27: putfield        com/google/android/gms/ads/internal/zza.zzvu:Z
        //    30: aload_0        
        //    31: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    34: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    37: ifnull          53
        //    40: aload_0        
        //    41: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    44: getfield        com/google/android/gms/ads/internal/zzbw.zzada:Lcom/google/android/gms/internal/ads/zzkh;
        //    47: iload_1        
        //    48: invokeinterface com/google/android/gms/internal/ads/zzkh.onAdFailedToLoad:(I)V
        //    53: aload_0        
        //    54: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    57: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    60: ifnull          76
        //    63: aload_0        
        //    64: getfield        com/google/android/gms/ads/internal/zza.zzvw:Lcom/google/android/gms/ads/internal/zzbw;
        //    67: getfield        com/google/android/gms/ads/internal/zzbw.zzadp:Lcom/google/android/gms/internal/ads/zzahe;
        //    70: iload_1        
        //    71: invokeinterface com/google/android/gms/internal/ads/zzahe.onRewardedVideoAdFailedToLoad:(I)V
        //    76: return         
        //    77: astore_3       
        //    78: ldc_w           "#007 Could not call remote method."
        //    81: aload_3        
        //    82: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    85: goto            53
        //    88: astore_3       
        //    89: ldc_w           "#007 Could not call remote method."
        //    92: aload_3        
        //    93: invokestatic    com/google/android/gms/internal/ads/zzakb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    96: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  40     53     77     88     Landroid/os/RemoteException;
        //  63     76     88     97     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected boolean zzc(final zzjj zzjj) {
        if (this.zzvw.zzacs == null) {
            return false;
        }
        final ViewParent parent = this.zzvw.zzacs.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        final View view = (View)parent;
        return zzbv.zzek().zza(view, view.getContext());
    }
    
    protected final void zzg(final View view) {
        final zzbx zzacs = this.zzvw.zzacs;
        if (zzacs != null) {
            zzacs.addView(view, zzbv.zzem().zzro());
        }
    }
    
    protected void zzi(final int n) {
        this.zzc(n, false);
    }
}
