// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.reward.zza;
import com.google.android.gms.ads.AdListener;
import android.content.Context;

@zzadh
public final class zzma
{
    private final Context mContext;
    private zzjd zzapt;
    private AdListener zzapu;
    private zza zzapv;
    private final zzxm zzast;
    private Correlator zzasx;
    private zzks zzasy;
    private OnCustomRenderedAdLoadedListener zzasz;
    private boolean zzatd;
    private RewardedVideoAdListener zzhc;
    private final zzjm zzuk;
    private AppEventListener zzvo;
    private String zzye;
    private boolean zzyu;
    
    public zzma(final Context context) {
        this(context, zzjm.zzara, null);
    }
    
    public zzma(final Context context, final PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzjm.zzara, publisherInterstitialAd);
    }
    
    @VisibleForTesting
    private zzma(final Context mContext, final zzjm zzuk, final PublisherInterstitialAd publisherInterstitialAd) {
        this.zzast = new zzxm();
        this.mContext = mContext;
        this.zzuk = zzuk;
    }
    
    private final void zzaj(final String s) {
        if (this.zzasy == null) {
            throw new IllegalStateException(new StringBuilder(String.valueOf(s).length() + 63).append("The ad unit ID must be set on InterstitialAd before ").append(s).append(" is called.").toString());
        }
    }
    
    public final AdListener getAdListener() {
        return this.zzapu;
    }
    
    public final String getAdUnitId() {
        return this.zzye;
    }
    
    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }
    
    public final String getMediationAdapterClassName() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.zzck();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
        return null;
    }
    
    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzasz;
    }
    
    public final boolean isLoaded() {
        try {
            return this.zzasy != null && this.zzasy.isReady();
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
            return false;
        }
    }
    
    public final boolean isLoading() {
        try {
            return this.zzasy != null && this.zzasy.isLoading();
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
            return false;
        }
    }
    
    public final void setAdListener(final AdListener zzapu) {
        try {
            this.zzapu = zzapu;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzjf zzjf;
                if (zzapu != null) {
                    zzjf = new zzjf(zzapu);
                }
                else {
                    zzjf = null;
                }
                zzasy.zza(zzjf);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void setAdUnitId(final String zzye) {
        if (this.zzye != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzye = zzye;
    }
    
    public final void setAppEventListener(final AppEventListener zzvo) {
        try {
            this.zzvo = zzvo;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzjp zzjp;
                if (zzvo != null) {
                    zzjp = new zzjp(zzvo);
                }
                else {
                    zzjp = null;
                }
                zzasy.zza(zzjp);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void setCorrelator(final Correlator zzasx) {
        this.zzasx = zzasx;
        try {
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzlg zzaz;
                if (this.zzasx == null) {
                    zzaz = null;
                }
                else {
                    zzaz = this.zzasx.zzaz();
                }
                zzasy.zza(zzaz);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void setImmersiveMode(final boolean b) {
        try {
            this.zzyu = b;
            if (this.zzasy != null) {
                this.zzasy.setImmersiveMode(b);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener zzasz) {
        try {
            this.zzasz = zzasz;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzog zzog;
                if (zzasz != null) {
                    zzog = new zzog(zzasz);
                }
                else {
                    zzog = null;
                }
                zzasy.zza(zzog);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void setRewardedVideoAdListener(final RewardedVideoAdListener zzhc) {
        try {
            this.zzhc = zzhc;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzahj zzahj;
                if (zzhc != null) {
                    zzahj = new zzahj(zzhc);
                }
                else {
                    zzahj = null;
                }
                zzasy.zza(zzahj);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void show() {
        try {
            this.zzaj("show");
            this.zzasy.showInterstitial();
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void zza(final zza zzapv) {
        try {
            this.zzapv = zzapv;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzji zzji;
                if (zzapv != null) {
                    zzji = new zzji(zzapv);
                }
                else {
                    zzji = null;
                }
                zzasy.zza(zzji);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void zza(final zzjd zzapt) {
        try {
            this.zzapt = zzapt;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzje zzje;
                if (zzapt != null) {
                    zzje = new zzje(zzapt);
                }
                else {
                    zzje = null;
                }
                zzasy.zza(zzje);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void zza(final zzlw zzlw) {
        try {
            if (this.zzasy == null) {
                if (this.zzye == null) {
                    this.zzaj("loadAd");
                }
                zzjn zzhx;
                if (this.zzatd) {
                    zzhx = zzjn.zzhx();
                }
                else {
                    zzhx = new zzjn();
                }
                final zzjr zzig = zzkb.zzig();
                final Context mContext = this.mContext;
                this.zzasy = zzjr.zza(mContext, false, (zzjr.zza<zzks>)new zzju(zzig, mContext, zzhx, this.zzye, this.zzast));
                if (this.zzapu != null) {
                    this.zzasy.zza(new zzjf(this.zzapu));
                }
                if (this.zzapt != null) {
                    this.zzasy.zza(new zzje(this.zzapt));
                }
                if (this.zzapv != null) {
                    this.zzasy.zza(new zzji(this.zzapv));
                }
                if (this.zzvo != null) {
                    this.zzasy.zza(new zzjp(this.zzvo));
                }
                if (this.zzasz != null) {
                    this.zzasy.zza(new zzog(this.zzasz));
                }
                if (this.zzasx != null) {
                    this.zzasy.zza(this.zzasx.zzaz());
                }
                if (this.zzhc != null) {
                    this.zzasy.zza(new zzahj(this.zzhc));
                }
                this.zzasy.setImmersiveMode(this.zzyu);
            }
            if (this.zzasy.zzb(zzjm.zza(this.mContext, zzlw))) {
                this.zzast.zzj(zzlw.zzir());
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
    }
    
    public final void zza(final boolean b) {
        this.zzatd = true;
    }
    
    public final Bundle zzba() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.zzba();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#008 Must be called on the main UI thread.", (Throwable)ex);
        }
        return new Bundle();
    }
}
