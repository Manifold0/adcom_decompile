// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import java.util.ArrayList;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zznk;
import java.lang.ref.WeakReference;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzlg;
import java.util.List;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrl;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzxn;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzkl;

@zzadh
@ParametersAreNonnullByDefault
public final class zzah extends zzkl
{
    private final Context mContext;
    private final Object mLock;
    private final zzw zzwc;
    private final zzxn zzwh;
    private final zzkh zzxs;
    @Nullable
    private final zzqw zzxt;
    @Nullable
    private final zzrl zzxu;
    @Nullable
    private final zzqz zzxv;
    @Nullable
    private final zzri zzxw;
    @Nullable
    private final zzjn zzxx;
    @Nullable
    private final PublisherAdViewOptions zzxy;
    private final SimpleArrayMap<String, zzrf> zzxz;
    private final SimpleArrayMap<String, zzrc> zzya;
    private final zzpl zzyb;
    private final List<String> zzyc;
    private final zzlg zzyd;
    private final String zzye;
    private final zzang zzyf;
    @Nullable
    private WeakReference<zzd> zzyg;
    
    zzah(final Context mContext, final String zzye, final zzxn zzwh, final zzang zzyf, final zzkh zzxs, final zzqw zzxt, final zzrl zzxu, final zzqz zzxv, final SimpleArrayMap<String, zzrf> zzxz, final SimpleArrayMap<String, zzrc> zzya, final zzpl zzyb, final zzlg zzyd, final zzw zzwc, final zzri zzxw, final zzjn zzxx, final PublisherAdViewOptions zzxy) {
        this.mLock = new Object();
        this.mContext = mContext;
        this.zzye = zzye;
        this.zzwh = zzwh;
        this.zzyf = zzyf;
        this.zzxs = zzxs;
        this.zzxv = zzxv;
        this.zzxt = zzxt;
        this.zzxu = zzxu;
        this.zzxz = zzxz;
        this.zzya = zzya;
        this.zzyb = zzyb;
        this.zzyc = this.zzdg();
        this.zzyd = zzyd;
        this.zzwc = zzwc;
        this.zzxw = zzxw;
        this.zzxx = zzxx;
        this.zzxy = zzxy;
        zznk.initialize(this.mContext);
    }
    
    private static void runOnUiThread(final Runnable runnable) {
        zzakk.zzcrm.post(runnable);
    }
    
    private final void zzb(final zzjj zzjj, final int n) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbcg) && this.zzxu != null) {
            this.zzi(0);
            return;
        }
        final zzbc zzbc = new zzbc(this.mContext, this.zzwc, zzjn.zzf(this.mContext), this.zzye, this.zzwh, this.zzyf);
        this.zzyg = new WeakReference<zzd>(zzbc);
        final zzqw zzxt = this.zzxt;
        Preconditions.checkMainThread("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        zzbc.zzvw.zzade = zzxt;
        final zzrl zzxu = this.zzxu;
        Preconditions.checkMainThread("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
        zzbc.zzvw.zzadg = zzxu;
        final zzqz zzxv = this.zzxv;
        Preconditions.checkMainThread("setOnContentAdLoadedListener must be called on the main UI thread.");
        zzbc.zzvw.zzadf = zzxv;
        final SimpleArrayMap<String, zzrf> zzxz = this.zzxz;
        Preconditions.checkMainThread("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        zzbc.zzvw.zzadi = zzxz;
        zzbc.zza(this.zzxs);
        final SimpleArrayMap<String, zzrc> zzya = this.zzya;
        Preconditions.checkMainThread("setOnCustomClickListener must be called on the main UI thread.");
        zzbc.zzvw.zzadh = zzya;
        zzbc.zzd(this.zzdg());
        final zzpl zzyb = this.zzyb;
        Preconditions.checkMainThread("setNativeAdOptions must be called on the main UI thread.");
        zzbc.zzvw.zzadj = zzyb;
        zzbc.zza(this.zzyd);
        zzbc.zzj(n);
        zzbc.zzb(zzjj);
    }
    
    private final boolean zzde() {
        return (boolean)zzkb.zzik().zzd(zznk.zzaym) && this.zzxw != null;
    }
    
    private final boolean zzdf() {
        return this.zzxt != null || this.zzxv != null || this.zzxu != null || (this.zzxz != null && this.zzxz.size() > 0);
    }
    
    private final List<String> zzdg() {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.zzxv != null) {
            list.add("1");
        }
        if (this.zzxt != null) {
            list.add("2");
        }
        if (this.zzxu != null) {
            list.add("6");
        }
        if (this.zzxz.size() > 0) {
            list.add("3");
        }
        return list;
    }
    
    private final void zze(final zzjj zzjj) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbcg) && this.zzxu != null) {
            this.zzi(0);
            return;
        }
        final zzq zzq = new zzq(this.mContext, this.zzwc, this.zzxx, this.zzye, this.zzwh, this.zzyf);
        this.zzyg = new WeakReference<zzd>(zzq);
        final zzri zzxw = this.zzxw;
        Preconditions.checkMainThread("setOnPublisherAdViewLoadedListener must be called on the main UI thread.");
        zzq.zzvw.zzadm = zzxw;
        if (this.zzxy != null) {
            if (this.zzxy.zzbg() != null) {
                zzq.zza(this.zzxy.zzbg());
            }
            zzq.setManualImpressionsEnabled(this.zzxy.getManualImpressionsEnabled());
        }
        final zzqw zzxt = this.zzxt;
        Preconditions.checkMainThread("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        zzq.zzvw.zzade = zzxt;
        final zzrl zzxu = this.zzxu;
        Preconditions.checkMainThread("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
        zzq.zzvw.zzadg = zzxu;
        final zzqz zzxv = this.zzxv;
        Preconditions.checkMainThread("setOnContentAdLoadedListener must be called on the main UI thread.");
        zzq.zzvw.zzadf = zzxv;
        final SimpleArrayMap<String, zzrf> zzxz = this.zzxz;
        Preconditions.checkMainThread("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        zzq.zzvw.zzadi = zzxz;
        final SimpleArrayMap<String, zzrc> zzya = this.zzya;
        Preconditions.checkMainThread("setOnCustomClickListener must be called on the main UI thread.");
        zzq.zzvw.zzadh = zzya;
        final zzpl zzyb = this.zzyb;
        Preconditions.checkMainThread("setNativeAdOptions must be called on the main UI thread.");
        zzq.zzvw.zzadj = zzyb;
        zzq.zzd(this.zzdg());
        zzq.zza(this.zzxs);
        zzq.zza(this.zzyd);
        final ArrayList<Integer> list = new ArrayList<Integer>();
        if (this.zzdf()) {
            list.add(1);
        }
        if (this.zzxw != null) {
            list.add(2);
        }
        zzq.zze(list);
        if (this.zzdf()) {
            zzjj.extras.putBoolean("ina", true);
        }
        if (this.zzxw != null) {
            zzjj.extras.putBoolean("iba", true);
        }
        zzq.zzb(zzjj);
    }
    
    private final void zzi(final int n) {
        if (this.zzxs == null) {
            return;
        }
        try {
            this.zzxs.onAdFailedToLoad(0);
        }
        catch (RemoteException ex) {
            zzakb.zzc("Failed calling onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Nullable
    public final String getMediationAdapterClassName() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzyg == null) {
                    return null;
                }
                final zzd zzd = this.zzyg.get();
                if (zzd != null) {
                    return zzd.getMediationAdapterClassName();
                }
            }
            return null;
        }
    }
    
    public final boolean isLoading() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzyg == null) {
                    return false;
                }
                final zzd zzd = this.zzyg.get();
                if (zzd != null) {
                    return zzd.isLoading();
                }
            }
            return false;
        }
    }
    
    public final void zza(final zzjj zzjj, final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of ads has to be more than 0");
        }
        runOnUiThread(new zzaj(this, zzjj, n));
    }
    
    @Nullable
    public final String zzck() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzyg == null) {
                    return null;
                }
                final zzd zzd = this.zzyg.get();
                if (zzd != null) {
                    return zzd.zzck();
                }
            }
            return null;
        }
    }
    
    public final void zzd(final zzjj zzjj) {
        runOnUiThread(new zzai(this, zzjj));
    }
}
